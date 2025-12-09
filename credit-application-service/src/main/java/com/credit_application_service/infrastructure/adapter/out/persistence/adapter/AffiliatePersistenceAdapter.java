package com.credit_application_service.infrastructure.adapter.out.persistence.adapter;

import com.credit_application_service.application.port.out.AffiliatePersistencePort;
import com.credit_application_service.domain.model.Affiliate;
import com.credit_application_service.infrastructure.adapter.out.persistence.entity.AffiliateEntity;
import com.credit_application_service.infrastructure.adapter.out.persistence.mapper.PersistenceMapper;
import com.credit_application_service.infrastructure.adapter.out.persistence.repository.AffiliateRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AffiliatePersistenceAdapter implements AffiliatePersistencePort {

    private final AffiliateRepository repository;
    private final PersistenceMapper mapper;

    public AffiliatePersistenceAdapter(AffiliateRepository repository, PersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Affiliate> findByDocument(String document) {
        return repository.findByDocument(document).map(mapper::toDomain);
    }

    @Override
    public Affiliate save(Affiliate affiliate) {
        AffiliateEntity entity = mapper.toEntity(affiliate);
        AffiliateEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public boolean existsByDocument(String document) {
        return repository.existsByDocument(document);
    }
}