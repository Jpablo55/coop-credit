package com.credit_application_service.infrastructure.adapter.in.web.dto.response;

import java.math.BigDecimal;

public class CreditApplicationResponse {

    private Long id;
    private String status;
    private BigDecimal requestedAmount;
    private Integer termInMonths;
    private String affiliateDocument;

    // Datos de la evaluación (simplificados para la respuesta)
    private Boolean approved;
    private String rejectionReason;

    public CreditApplicationResponse() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public BigDecimal getRequestedAmount() { return requestedAmount; }
    public void setRequestedAmount(BigDecimal requestedAmount) { this.requestedAmount = requestedAmount; }
    public Integer getTermInMonths() { return termInMonths; }
    public void setTermInMonths(Integer termInMonths) { this.termInMonths = termInMonths; }
    public String getAffiliateDocument() { return affiliateDocument; }
    public void setAffiliateDocument(String affiliateDocument) { this.affiliateDocument = affiliateDocument; }
    public Boolean getApproved() { return approved; }
    public void setApproved(Boolean approved) { this.approved = approved; }
    public String getRejectionReason() { return rejectionReason; }
    public void setRejectionReason(String rejectionReason) { this.rejectionReason = rejectionReason; }
}
