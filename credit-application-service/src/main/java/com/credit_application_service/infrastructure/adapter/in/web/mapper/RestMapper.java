package com.credit_application_service.infrastructure.adapter.in.web.mapper;

import com.credit_application_service.domain.model.Affiliate;
import com.credit_application_service.domain.model.CreditApplication;
import com.credit_application_service.infrastructure.adapter.in.web.dto.request.AffiliateRequest;
import com.credit_application_service.infrastructure.adapter.in.web.dto.request.CreditApplicationRequest;
import com.credit_application_service.infrastructure.adapter.in.web.dto.response.CreditApplicationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestMapper {

    // Affiliate
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", constant = "ACTIVE") // Por defecto al crear
    @Mapping(target = "affiliationDate", expression = "java(java.time.LocalDate.now())")
    Affiliate toDomain(AffiliateRequest request);

    // Credit Application Request -> Domain
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "applicationDate", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "status", constant = "PENDING")
    @Mapping(target = "proposedRate", constant = "0.0") // Podría ser calculado o venir en request
    @Mapping(target = "riskEvaluation", ignore = true)
    CreditApplication toDomain(CreditApplicationRequest request);

    // Domain -> Credit Application Response
    @Mapping(source = "riskEvaluation.isApproved", target = "approved")
    @Mapping(source = "riskEvaluation.rejectionReason", target = "rejectionReason")
    CreditApplicationResponse toResponse(CreditApplication domain);
}