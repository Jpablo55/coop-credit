package com.credit_application_service.domain.usecase;

import com.credit_application_service.application.port.in.CreditApplicationServicePort;
import com.credit_application_service.application.port.out.AffiliatePersistencePort;
import com.credit_application_service.application.port.out.CreditApplicationPersistencePort;
import com.credit_application_service.application.port.out.RiskCentralPort;
import com.credit_application_service.domain.exception.ValidationException;
import com.credit_application_service.domain.exception.ResourceNotFoundException;
import com.credit_application_service.domain.model.Affiliate;
import com.credit_application_service.domain.model.CreditApplication;
import com.credit_application_service.domain.model.RiskEvaluation;
import com.credit_application_service.domain.model.enums.ApplicationStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CreditApplicationUseCase implements CreditApplicationServicePort {

    private final AffiliatePersistencePort affiliatePort;
    private final CreditApplicationPersistencePort applicationPort;
    private final RiskCentralPort riskCentralPort;

    private static final int MIN_AFFILIATION_MONTHS = 6;
    private static final BigDecimal MAX_DEBT_TO_INCOME_RATIO = new BigDecimal("0.40");
    private static final BigDecimal MAX_AMOUNT_MULTIPLIER = new BigDecimal("10");

    public CreditApplicationUseCase(AffiliatePersistencePort affiliatePort,
                                    CreditApplicationPersistencePort applicationPort,
                                    RiskCentralPort riskCentralPort) {
        this.affiliatePort = affiliatePort;
        this.applicationPort = applicationPort;
        this.riskCentralPort = riskCentralPort;
    }

    @Override
    @Transactional
    public CreditApplication registerAndEvaluateApplication(CreditApplication application) {
        // Validar Afiliado
        Affiliate affiliate = affiliatePort.findByDocument(application.getAffiliateDocument())
                .orElseThrow(() -> new ResourceNotFoundException("Affiliate not found with document: " + application.getAffiliateDocument()));

        if (!affiliate.isActive()) {
            throw new ValidationException("Affiliate must be ACTIVE to apply for credit.");
        }

        // Guardar solicitud inicial (PENDING)
        CreditApplication savedApplication = applicationPort.saveApplication(application);

        //  Consultar Riesgo Externo (Puerto de Salida)
        RiskEvaluation externalEvaluation = riskCentralPort.fetchRiskScore(
                affiliate.getDocument(),
                application.getRequestedAmount(),
                application.getTermInMonths()
        );

        RiskEvaluation finalEvaluation = applyInternalPolicies(affiliate, savedApplication, externalEvaluation);

        applicationPort.saveEvaluation(finalEvaluation);

        if (finalEvaluation.getIsApproved()) {
            savedApplication.approve(finalEvaluation);
        } else {
            savedApplication.reject(finalEvaluation, finalEvaluation.getRejectionReason());
        }

        return applicationPort.saveApplication(savedApplication);
    }


    @Override
    public List<CreditApplication> findPendingApplications() {
        return applicationPort.findByStatus(ApplicationStatus.PENDING.name());
    }

    @Override
    public List<CreditApplication> findApplicationsByAffiliateDocument(String document) {
        return applicationPort.findByAffiliateDocument(document);
    }


    private RiskEvaluation applyInternalPolicies(Affiliate affiliate, CreditApplication application, RiskEvaluation external) {
        external.setIsApproved(true);
        StringBuilder reasons = new StringBuilder();

        // Antigüedad
        if (!affiliate.isMinimumAffiliationTimeMet(MIN_AFFILIATION_MONTHS)) {
            external.setIsApproved(false);
            reasons.append("Affiliate does not meet minimum affiliation time of ").append(MIN_AFFILIATION_MONTHS).append(" months. ");
        }

        // Capacidad de endeudamiento (Cuota estimada vs Ingresos)
        BigDecimal estimatedMonthlyPayment = application.getRequestedAmount()
                .divide(new BigDecimal(application.getTermInMonths()), 2, BigDecimal.ROUND_HALF_UP);

        BigDecimal maxMonthlyDebt = affiliate.getSalary().multiply(MAX_DEBT_TO_INCOME_RATIO);

        if (estimatedMonthlyPayment.compareTo(maxMonthlyDebt) > 0) {
            external.setIsApproved(false);
            reasons.append("Estimated monthly payment exceeds 40% of salary. ");
        }

        // Monto máximo
        BigDecimal maxAmount = affiliate.getSalary().multiply(MAX_AMOUNT_MULTIPLIER);
        if (application.getRequestedAmount().compareTo(maxAmount) > 0) {
            external.setIsApproved(false);
            reasons.append("Amount exceeds 10x salary limit. ");
        }

        // Riesgo Externo Alto
        if ("ALTO RIESGO".equalsIgnoreCase(external.getRiskLevel())) {
            external.setIsApproved(false);
            reasons.append("External risk evaluation is HIGH. ");
        }

        if (!external.getIsApproved()) {
            external.setRejectionReason(reasons.toString());
        }

        return external;
    }
}