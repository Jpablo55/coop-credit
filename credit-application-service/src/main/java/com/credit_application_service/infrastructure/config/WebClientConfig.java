package com.credit_application_service.infrastructure.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${risk.central.url}")
    private String riskCentralUrl;

    @Bean
    public WebClient riskCentralWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl(riskCentralUrl)
                .build();
    }
}
