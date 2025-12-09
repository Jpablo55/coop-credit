package com.credit_application_service.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "credit_applications")
public class CreditApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación N-1 con Afiliado
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "affiliate_id", nullable = false)
    private AffiliateEntity affiliate;

    private BigDecimal requestedAmount;
    private Integer termInMonths;
    private BigDecimal proposedRate;
    private LocalDate applicationDate;
    private String status;

    // Relación 1-1 con Evaluación de Riesgo [cite: 126]
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "risk_evaluation_id", referencedColumnName = "id")
    private RiskEvaluationEntity riskEvaluation;

    public CreditApplicationEntity() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public AffiliateEntity getAffiliate() { return affiliate; }
    public void setAffiliate(AffiliateEntity affiliate) { this.affiliate = affiliate; }
    public BigDecimal getRequestedAmount() { return requestedAmount; }
    public void setRequestedAmount(BigDecimal requestedAmount) { this.requestedAmount = requestedAmount; }
    public Integer getTermInMonths() { return termInMonths; }
    public void setTermInMonths(Integer termInMonths) { this.termInMonths = termInMonths; }
    public BigDecimal getProposedRate() { return proposedRate; }
    public void setProposedRate(BigDecimal proposedRate) { this.proposedRate = proposedRate; }
    public LocalDate getApplicationDate() { return applicationDate; }
    public void setApplicationDate(LocalDate applicationDate) { this.applicationDate = applicationDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public RiskEvaluationEntity getRiskEvaluation() { return riskEvaluation; }
    public void setRiskEvaluation(RiskEvaluationEntity riskEvaluation) { this.riskEvaluation = riskEvaluation; }
}