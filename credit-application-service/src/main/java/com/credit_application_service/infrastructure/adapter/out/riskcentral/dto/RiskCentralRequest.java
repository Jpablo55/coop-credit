package com.credit_application_service.infrastructure.adapter.out.riskcentral.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public class RiskCentralRequest {

    @JsonProperty("documento")
    private String documento;

    @JsonProperty("monto")
    private BigDecimal monto;

    @JsonProperty("plazo")
    private Integer plazo;

    public RiskCentralRequest(String documento, BigDecimal monto, Integer plazo) {
        this.documento = documento;
        this.monto = monto;
        this.plazo = plazo;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Integer getPlazo() {
        return plazo;
    }

    public void setPlazo(Integer plazo) {
        this.plazo = plazo;
    }
}