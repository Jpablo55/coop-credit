package com.credit_application_service.infrastructure.adapter.in.web.controller;

import com.credit_application_service.application.port.in.AffiliateManagementServicePort;
import com.credit_application_service.domain.model.Affiliate;
import com.credit_application_service.infrastructure.adapter.in.web.dto.request.AffiliateRequest;
import com.credit_application_service.infrastructure.adapter.in.web.mapper.RestMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/affiliates")
public class AffiliateController {

    private final AffiliateManagementServicePort servicePort;
    private final RestMapper mapper;

    public AffiliateController(AffiliateManagementServicePort servicePort, RestMapper mapper) {
        this.servicePort = servicePort;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<Void> registerAffiliate(@Valid @RequestBody AffiliateRequest request) {
        Affiliate domainAffiliate = mapper.toDomain(request);
        servicePort.registerAffiliate(domainAffiliate);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}