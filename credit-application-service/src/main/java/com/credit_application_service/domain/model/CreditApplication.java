package com.credit_application_service.domain.model;


import com.credit_application_service.domain.model.enums.ApplicationStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreditApplication {

    private Long id;

    private String affiliateDocument;

    private BigDecimal requestedAmount;

    private Integer termInMonths;

    private BigDecimal proposedRate;

    private LocalDate applicationDate;

    private ApplicationStatus status;

    private RiskEvaluation riskEvaluation;


    public CreditApplication() {
        this.applicationDate = LocalDate.now();
        this.status = ApplicationStatus.PENDING;
    }

    public CreditApplication(String affiliateDocument, BigDecimal requestedAmount, Integer termInMonths, BigDecimal proposedRate) {
        this();
        this.affiliateDocument = affiliateDocument;
        this.requestedAmount = requestedAmount;
        this.termInMonths = termInMonths;
        this.proposedRate = proposedRate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAffiliateDocument() {
        return affiliateDocument;
    }

    public void setAffiliateDocument(String affiliateDocument) {
        this.affiliateDocument = affiliateDocument;
    }

    public BigDecimal getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(BigDecimal requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public Integer getTermInMonths() {
        return termInMonths;
    }

    public void setTermInMonths(Integer termInMonths) {
        this.termInMonths = termInMonths;
    }

    public BigDecimal getProposedRate() {
        return proposedRate;
    }

    public void setProposedRate(BigDecimal proposedRate) {
        this.proposedRate = proposedRate;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public RiskEvaluation getRiskEvaluation() {
        return riskEvaluation;
    }

    public void setRiskEvaluation(RiskEvaluation riskEvaluation) {
        this.riskEvaluation = riskEvaluation;
    }

    public void approve(RiskEvaluation evaluation) {
        this.status = ApplicationStatus.APPROVED;
        this.riskEvaluation = evaluation;
    }

    public void reject(RiskEvaluation evaluation, String reason) {
        this.status = ApplicationStatus.REJECTED;
        this.riskEvaluation = evaluation;

    }
}