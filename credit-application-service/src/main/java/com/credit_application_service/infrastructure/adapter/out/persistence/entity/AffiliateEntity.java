package com.credit_application_service.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "affiliates")
public class AffiliateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String document;

    private String name;
    private BigDecimal salary;
    private LocalDate affiliationDate;
    private String status; // Mapeado como String para simplicidad en BD

    // Relación 1-N (Un afiliado tiene muchas solicitudes)
    // FetchType.LAZY es crucial para el rendimiento
    @OneToMany(mappedBy = "affiliate", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CreditApplicationEntity> applications;

    public AffiliateEntity() {}

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
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<CreditApplicationEntity> getApplications() { return applications; }
    public void setApplications(List<CreditApplicationEntity> applications) { this.applications = applications; }
}
