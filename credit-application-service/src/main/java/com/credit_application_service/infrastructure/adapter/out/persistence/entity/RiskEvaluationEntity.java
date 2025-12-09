package com.credit_application_service.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "risk_evaluations")
public class RiskEvaluationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String document;
    private Integer score;
    private String riskLevel;
    private String riskDetail;
    private Boolean isApproved;
    private String rejectionReason;
    private LocalDateTime evaluationDate;

    // Relación 1-1 Inversa (opcional, pero útil si se quiere navegar desde la evaluación a la solicitud)
    @OneToOne(mappedBy = "riskEvaluation")
    private CreditApplicationEntity creditApplication;

    public RiskEvaluationEntity() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDocument() { return document; }
    public void setDocument(String document) { this.document = document; }
    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }
    public String getRiskLevel() { return riskLevel; }
    public void setRiskLevel(String riskLevel) { this.riskLevel = riskLevel; }
    public String getRiskDetail() { return riskDetail; }
    public void setRiskDetail(String riskDetail) { this.riskDetail = riskDetail; }
    public Boolean getIsApproved() { return isApproved; }
    public void setIsApproved(Boolean isApproved) { this.isApproved = isApproved; }
    public String getRejectionReason() { return rejectionReason; }
    public void setRejectionReason(String rejectionReason) { this.rejectionReason = rejectionReason; }
    public LocalDateTime getEvaluationDate() { return evaluationDate; }
    public void setEvaluationDate(LocalDateTime evaluationDate) { this.evaluationDate = evaluationDate; }
    public CreditApplicationEntity getCreditApplication() { return creditApplication; }
    public void setCreditApplication(CreditApplicationEntity creditApplication) { this.creditApplication = creditApplication; }
}