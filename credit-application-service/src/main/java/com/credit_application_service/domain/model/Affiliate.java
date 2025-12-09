package com.credit_application_service.domain.model;

import com.credit_application_service.domain.model.enums.AffiliateStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Affiliate {

    private Long id;
    private String document;
    private String name;
    private BigDecimal salary;
    private LocalDate affiliationDate;
    private AffiliateStatus status;

    public Affiliate() {
    }

    public Affiliate(Long id, String document, String name, BigDecimal salary, LocalDate affiliationDate, AffiliateStatus status) {
        this.id = id;
        this.document = document;
        this.name = name;
        this.salary = salary;
        this.affiliationDate = affiliationDate;
        this.status = status;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDocument() { return document; }
    public void setDocument(String document) { this.document = document; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public BigDecimal getSalary() { return salary; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }
    public LocalDate getAffiliationDate() { return affiliationDate; }
    public void setAffiliationDate(LocalDate affiliationDate) { this.affiliationDate = affiliationDate; }
    public AffiliateStatus getStatus() { return status; }
    public void setStatus(AffiliateStatus status) { this.status = status; }

    public boolean isActive() {
        return this.status == AffiliateStatus.ACTIVE;
    }

    public boolean isMinimumAffiliationTimeMet(int minimumMonths) {
        if (this.affiliationDate == null) return false;
        return this.affiliationDate.isBefore(LocalDate.now().minusMonths(minimumMonths));
    }
}