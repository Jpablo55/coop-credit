package com.credit_application_service.infrastructure.adapter.out.riskcentral.adapter;

import com.credit_application_service.application.port.out.RiskCentralPort;
import com.credit_application_service.domain.model.RiskEvaluation;
import com.credit_application_service.infrastructure.adapter.out.riskcentral.dto.RiskCentralRequest;
import com.credit_application_service.infrastructure.adapter.out.riskcentral.dto.RiskCentralResponse;
import com.credit_application_service.infrastructure.adapter.out.riskcentral.mapper.RiskCentralMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;

@Component
public class RiskCentralAdapter implements RiskCentralPort {

    private final WebClient webClient;
    private final RiskCentralMapper mapper;

    public RiskCentralAdapter(WebClient riskCentralWebClient, RiskCentralMapper mapper) {
        this.webClient = riskCentralWebClient;
        this.mapper = mapper;
    }

    @Override
    public RiskEvaluation fetchRiskScore(String document, BigDecimal amount, Integer termInMonths) {

        RiskCentralRequest request = new RiskCentralRequest(document, amount, termInMonths);

        RiskCentralResponse response = webClient.post()
                .uri("/risk-evaluation")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(RiskCentralResponse.class)
                .block();

        if (response == null) {
            throw new RuntimeException("Error communicating with Risk Central Service");
        }

        return mapper.toDomain(response);
    }
}
