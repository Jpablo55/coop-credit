package com.credit_application_service.application.port.out;

import com.credit_application_service.domain.model.User;

import java.util.Optional;

public interface UserPersistencePort {
    Optional<User> findByUsername(String username);
    User save(User user);
}
