package com.credit_application_service.infrastructure.adapter.out.persistence.repository;

import com.credit_application_service.infrastructure.adapter.out.persistence.entity.AffiliateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AffiliateRepository extends JpaRepository<AffiliateEntity, Long> {
    Optional<AffiliateEntity> findByDocument(String document);
    boolean existsByDocument(String document);
}