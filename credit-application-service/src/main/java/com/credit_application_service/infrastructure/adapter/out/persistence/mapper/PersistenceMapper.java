package com.credit_application_service.infrastructure.adapter.out.persistence.mapper;

import com.credit_application_service.domain.model.Affiliate;
import com.credit_application_service.domain.model.CreditApplication;
import com.credit_application_service.domain.model.RiskEvaluation;
import com.credit_application_service.domain.model.User;
import com.credit_application_service.domain.model.enums.Role;
import com.credit_application_service.infrastructure.adapter.out.persistence.entity.AffiliateEntity;
import com.credit_application_service.infrastructure.adapter.out.persistence.entity.CreditApplicationEntity;
import com.credit_application_service.infrastructure.adapter.out.persistence.entity.RiskEvaluationEntity;
import com.credit_application_service.infrastructure.adapter.out.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersistenceMapper {

    // Affiliate Mappings
    Affiliate toDomain(AffiliateEntity entity);
    AffiliateEntity toEntity(Affiliate domain);

    // RiskEvaluation Mappings
    RiskEvaluation toDomain(RiskEvaluationEntity entity);
    RiskEvaluationEntity toEntity(RiskEvaluation domain);

    // CreditApplication Mappings
    @Mapping(source = "affiliate.document", target = "affiliateDocument")
    CreditApplication toDomain(CreditApplicationEntity entity);

    @Mapping(target = "affiliate", ignore = true) // Se maneja manualmente en el adaptador
    CreditApplicationEntity toEntity(CreditApplication domain);

    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapRolesToStrings")
    UserEntity toEntity(User user);

    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapStringsToRoles")
    User toDomain(UserEntity entity);

    // Métodos auxiliares para convertir Set<Role> enum a Set<String> y viceversa
    @org.mapstruct.Named("mapRolesToStrings")
    default java.util.Set<String> mapRolesToStrings(java.util.Set<Role> roles) {
        if (roles == null) return null;
        return roles.stream().map(Enum::name).collect(java.util.stream.Collectors.toSet());
    }

    @org.mapstruct.Named("mapStringsToRoles")
    default java.util.Set<Role> mapStringsToRoles(java.util.Set<String> roles) {
        if (roles == null) return null;
        return roles.stream().map(Role::valueOf).collect(java.util.stream.Collectors.toSet());
    }
}