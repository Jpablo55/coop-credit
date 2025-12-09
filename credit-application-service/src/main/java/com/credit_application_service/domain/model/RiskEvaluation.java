package com.credit_application_service.domain.model;

import java.time.LocalDateTime;

public class RiskEvaluation {

    private Long id;
    private String document;
    private Integer score;
    private String riskLevel;
    private String riskDetail;
    private Boolean isApproved; // Campo wrapper
    private String rejectionReason;
    private LocalDateTime evaluationDate;

    public RiskEvaluation() {
        this.evaluationDate = LocalDateTime.now();
    }

    public RiskEvaluation(Long id, String document, Integer score, String riskLevel, String riskDetail, Boolean isApproved, String rejectionReason) {
        this.id = id;
        this.document = document;
        this.score = score;
        this.riskLevel = riskLevel;
        this.riskDetail = riskDetail;
        this.isApproved = isApproved;
        this.rejectionReason = rejectionReason;
        this.evaluationDate = LocalDateTime.now();
    }

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
}