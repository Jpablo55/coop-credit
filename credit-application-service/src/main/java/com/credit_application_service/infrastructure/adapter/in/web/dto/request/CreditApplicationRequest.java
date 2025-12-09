package com.credit_application_service.infrastructure.adapter.in.web.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CreditApplicationRequest {

    @NotBlank(message = "Affiliate document is required")
    private String affiliateDocument;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal requestedAmount;

    @NotNull(message = "Term is required")
    @Min(value = 1, message = "Term must be at least 1 month")
    private Integer termInMonths;

    public CreditApplicationRequest() {}

    // Getters y Setters
    public String getAffiliateDocument() { return affiliateDocument; }
    public void setAffiliateDocument(String affiliateDocument) { this.affiliateDocument = affiliateDocument; }
    public BigDecimal getRequestedAmount() { return requestedAmount; }
    public void setRequestedAmount(BigDecimal requestedAmount) { this.requestedAmount = requestedAmount; }
    public Integer getTermInMonths() { return termInMonths; }
    public void setTermInMonths(Integer termInMonths) { this.termInMonths = termInMonths; }
}
