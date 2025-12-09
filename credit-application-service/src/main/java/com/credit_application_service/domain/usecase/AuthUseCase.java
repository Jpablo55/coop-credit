package com.credit_application_service.domain.usecase;

import com.credit_application_service.application.port.in.AuthenticationServicePort;
import com.credit_application_service.application.port.out.UserPersistencePort;
import com.credit_application_service.domain.exception.ValidationException;
import com.credit_application_service.domain.model.User;
import com.credit_application_service.domain.model.enums.Role;
import com.credit_application_service.infrastructure.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthUseCase implements AuthenticationServicePort {

    private final UserPersistencePort userPort;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthUseCase(UserPersistencePort userPort, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userPort = userPort;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public User registerUser(String username, String password, Role role) {
        if (userPort.findByUsername(username).isPresent()) {
            throw new ValidationException("Username already exists");
        }

        Set<Role> roles = new HashSet<>();
        roles.add(role);

        User newUser = new User(null, username, passwordEncoder.encode(password), roles);
        return userPort.save(newUser);
    }

    @Override
    public String login(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        var user = userPort.findByUsername(username).orElseThrow();

        org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.emptyList()
        );

        return jwtService.generateToken(userDetails);
    }
}