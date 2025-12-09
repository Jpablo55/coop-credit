package com.credit_application_service.application.port.in;

import com.credit_application_service.domain.model.Affiliate;
import com.credit_application_service.domain.model.CreditApplication;

import java.util.List;

public interface CreditApplicationServicePort {


    CreditApplication registerAndEvaluateApplication(CreditApplication application);


    List<CreditApplication> findPendingApplications();

    List<CreditApplication> findApplicationsByAffiliateDocument(String document);

}