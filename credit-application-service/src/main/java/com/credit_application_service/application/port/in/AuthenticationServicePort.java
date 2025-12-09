package com.credit_application_service.application.port.in;

import com.credit_application_service.domain.model.User;
import com.credit_application_service.domain.model.enums.Role;

public interface AuthenticationServicePort {

    User registerUser(String username, String password, Role role);

    String login(String username, String password);
}
