package com.credit_application_service.application.port.out;

import com.credit_application_service.domain.model.RiskEvaluation;

import java.math.BigDecimal;


public interface RiskCentralPort {

    RiskEvaluation fetchRiskScore(String document, java.math.BigDecimal amount, Integer termInMonths);
}
