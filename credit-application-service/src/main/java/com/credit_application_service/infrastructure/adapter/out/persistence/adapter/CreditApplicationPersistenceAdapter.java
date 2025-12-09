package com.credit_application_service.infrastructure.adapter.out.persistence.adapter;

import com.credit_application_service.application.port.out.CreditApplicationPersistencePort;
import com.credit_application_service.domain.model.CreditApplication;
import com.credit_application_service.domain.model.RiskEvaluation;
import com.credit_application_service.infrastructure.adapter.out.persistence.entity.AffiliateEntity;
import com.credit_application_service.infrastructure.adapter.out.persistence.entity.CreditApplicationEntity;
import com.credit_application_service.infrastructure.adapter.out.persistence.mapper.PersistenceMapper;
import com.credit_application_service.infrastructure.adapter.out.persistence.repository.AffiliateRepository;
import com.credit_application_service.infrastructure.adapter.out.persistence.repository.CreditApplicationRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CreditApplicationPersistenceAdapter implements CreditApplicationPersistencePort {

    private final CreditApplicationRepository applicationRepository;
    private final AffiliateRepository affiliateRepository;
    private final PersistenceMapper mapper;

    public CreditApplicationPersistenceAdapter(CreditApplicationRepository applicationRepository,
                                               AffiliateRepository affiliateRepository,
                                               PersistenceMapper mapper) {
        this.applicationRepository = applicationRepository;
        this.affiliateRepository = affiliateRepository;
        this.mapper = mapper;
    }

    @Override
    public CreditApplication saveApplication(CreditApplication application) {
        CreditApplicationEntity entity = mapper.toEntity(application);

        // Resolver la relación con Affiliate manualmente usando el affiliateDocument
        if (application.getAffiliateDocument() != null) {
            AffiliateEntity affiliateEntity = affiliateRepository.findByDocument(application.getAffiliateDocument())
                    .orElseThrow(() -> new RuntimeException("Affiliate persistence consistency error"));
            entity.setAffiliate(affiliateEntity);
        }

        CreditApplicationEntity saved = applicationRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public RiskEvaluation saveEvaluation(RiskEvaluation evaluation) {
        return evaluation;
    }

    @Override
    public Optional<CreditApplication> findById(Long id) {
        return applicationRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<CreditApplication> findByStatus(String status) {
        return applicationRepository.findByStatus(status).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditApplication> findByAffiliateDocument(String document) {
        return applicationRepository.findByAffiliateDocument(document).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}