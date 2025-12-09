package com.credit_application_service.infrastructure.adapter.out.persistence.repository;

import com.credit_application_service.infrastructure.adapter.out.persistence.entity.CreditApplicationEntity;
import org.springframework.data.jpa.repository.EntityGraph; // Para evitar N+1
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CreditApplicationRepository extends JpaRepository<CreditApplicationEntity, Long> {

    @EntityGraph(attributePaths = {"affiliate", "riskEvaluation"})
    List<CreditApplicationEntity> findByStatus(String status);

    @EntityGraph(attributePaths = {"affiliate", "riskEvaluation"})
    List<CreditApplicationEntity> findByAffiliateDocument(String document);
}