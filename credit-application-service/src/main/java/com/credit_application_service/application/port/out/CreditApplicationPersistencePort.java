package com.credit_application_service.application.port.out;

import com.credit_application_service.domain.model.CreditApplication;
import com.credit_application_service.domain.model.RiskEvaluation;

import java.util.List;
import java.util.Optional;

public interface CreditApplicationPersistencePort {
    CreditApplication saveApplication(CreditApplication application);
    RiskEvaluation saveEvaluation(RiskEvaluation evaluation);
    Optional<CreditApplication> findById(Long id);
    List<CreditApplication> findByStatus(String status);
    List<CreditApplication> findByAffiliateDocument(String document);
}
