package com.credit_application_service.infrastructure.adapter.out.riskcentral.mapper;

import com.credit_application_service.domain.model.RiskEvaluation;
import com.credit_application_service.infrastructure.adapter.out.riskcentral.dto.RiskCentralResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RiskCentralMapper {

    @Mapping(source = "score", target = "score")
    @Mapping(source = "nivelRiesgo", target = "riskLevel")
    @Mapping(source = "detalle", target = "riskDetail")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isApproved", ignore = true) // Se decide en el dominio
    @Mapping(target = "rejectionReason", ignore = true)
    @Mapping(target = "evaluationDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(source = "documento", target = "document")
    RiskEvaluation toDomain(RiskCentralResponse response);
}