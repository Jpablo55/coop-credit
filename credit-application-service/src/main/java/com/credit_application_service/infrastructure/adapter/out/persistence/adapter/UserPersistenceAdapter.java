package com.credit_application_service.infrastructure.adapter.out.persistence.adapter;

import com.credit_application_service.application.port.out.UserPersistencePort;
import com.credit_application_service.domain.model.User;
import com.credit_application_service.infrastructure.adapter.out.persistence.entity.UserEntity;
import com.credit_application_service.infrastructure.adapter.out.persistence.mapper.PersistenceMapper;
import com.credit_application_service.infrastructure.adapter.out.persistence.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserPersistenceAdapter implements UserPersistencePort {

    private final UserRepository repository;
    private final PersistenceMapper mapper;

    public UserPersistenceAdapter(UserRepository repository, PersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username).map(mapper::toDomain);
    }

    @Override
    public User save(User user) {
        UserEntity entity = mapper.toEntity(user);
        return mapper.toDomain(repository.save(entity));
    }
}
