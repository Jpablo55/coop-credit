package com.credit_application_service.infrastructure.adapter.out.riskcentral.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class RiskCentralResponse {

    @JsonProperty("documento")
    private String documento;

    @JsonProperty("score")
    private Integer score;

    @JsonProperty("nivelRiesgo")
    private String nivelRiesgo;

    @JsonProperty("detalle")
    private String detalle;

    public RiskCentralResponse() {}

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }
    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }
    public String getNivelRiesgo() { return nivelRiesgo; }
    public void setNivelRiesgo(String nivelRiesgo) { this.nivelRiesgo = nivelRiesgo; }
    public String getDetalle() { return detalle; }
    public void setDetalle(String detalle) { this.detalle = detalle; }
}