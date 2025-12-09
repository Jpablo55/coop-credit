package com.credit_application_service.domain.usecase;

import com.credit_application_service.application.port.in.AffiliateManagementServicePort;
import com.credit_application_service.application.port.out.AffiliatePersistencePort;
import com.credit_application_service.domain.exception.ValidationException;
import com.credit_application_service.domain.model.Affiliate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AffiliateUseCase implements AffiliateManagementServicePort {

    private final AffiliatePersistencePort affiliatePersistencePort;

    public AffiliateUseCase(AffiliatePersistencePort affiliatePersistencePort) {
        this.affiliatePersistencePort = affiliatePersistencePort;
    }

    @Override
    public Affiliate registerAffiliate(Affiliate affiliate) {
        if (affiliatePersistencePort.existsByDocument(affiliate.getDocument())) {
            throw new ValidationException("Affiliate with this document already exists.");
        }

        if (affiliate.getSalary().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationException("Salary must be greater than zero.");
        }

        return affiliatePersistencePort.save(affiliate);
    }

    @Override
    public Affiliate updateAffiliate(Affiliate affiliate) {
        // Validar existencia antes de actualizar
        if (!affiliatePersistencePort.existsByDocument(affiliate.getDocument())) {
            throw new ValidationException("Affiliate not found.");
        }
        return affiliatePersistencePort.save(affiliate);
    }
}