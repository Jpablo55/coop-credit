package com.credit_application_service.application.port.in;

import com.credit_application_service.domain.model.Affiliate;

public interface AffiliateManagementServicePort {

    Affiliate registerAffiliate(Affiliate affiliate);

    Affiliate updateAffiliate(Affiliate affiliate);
}