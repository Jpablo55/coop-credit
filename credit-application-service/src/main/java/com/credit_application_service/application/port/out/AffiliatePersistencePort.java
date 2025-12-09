package com.credit_application_service.application.port.out;

import com.credit_application_service.domain.model.Affiliate;

import java.util.Optional;

public interface AffiliatePersistencePort {
    Optional<Affiliate> findByDocument(String document);
    Affiliate save(Affiliate affiliate);
    boolean existsByDocument(String document);
}
