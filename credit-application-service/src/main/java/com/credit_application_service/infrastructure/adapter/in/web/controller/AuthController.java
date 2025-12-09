package com.credit_application_service.infrastructure.adapter.in.web.controller;

import com.credit_application_service.application.port.in.AuthenticationServicePort;
import com.credit_application_service.domain.model.enums.Role;
import com.credit_application_service.infrastructure.adapter.in.web.dto.request.AuthRequest;
import com.credit_application_service.infrastructure.adapter.in.web.dto.response.AuthResponse;
import com.credit_application_service.infrastructure.adapter.in.web.dto.request.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationServicePort authService;

    public AuthController(AuthenticationServicePort authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequest request) {
        // Convertir String a Enum Role de forma segura
        Role roleEnum = Role.valueOf(request.getRole().toUpperCase());

        authService.registerUser(request.getUsername(), request.getPassword(), roleEnum);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        String token = authService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
