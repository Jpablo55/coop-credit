package com.credit_application_service.infrastructure.adapter.in.web.controller;

import com.credit_application_service.application.port.in.CreditApplicationServicePort;
import com.credit_application_service.domain.model.CreditApplication;
import com.credit_application_service.infrastructure.adapter.in.web.dto.response.CreditApplicationResponse;
import com.credit_application_service.infrastructure.adapter.in.web.dto.request.CreditApplicationRequest;
import com.credit_application_service.infrastructure.adapter.in.web.mapper.RestMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/credit-applications")
public class CreditApplicationController {

    private final CreditApplicationServicePort servicePort;
    private final RestMapper mapper;

    public CreditApplicationController(CreditApplicationServicePort servicePort, RestMapper mapper) {
        this.servicePort = servicePort;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<CreditApplicationResponse> createApplication(@Valid @RequestBody CreditApplicationRequest request) {
        // Convertir DTO a Dominio
        CreditApplication application = mapper.toDomain(request);

        // Invocar el Caso de Uso (Lógica de Negocio)
        CreditApplication result = servicePort.registerAndEvaluateApplication(application);

        // Convertir Resultado a DTO
        CreditApplicationResponse response = mapper.toResponse(result);

        return ResponseEntity.ok(response);
    }
}