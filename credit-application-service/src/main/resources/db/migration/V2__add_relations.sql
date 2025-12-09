-- Relación User -> Roles
ALTER TABLE user_roles
    ADD CONSTRAINT fk_user_roles_user
    FOREIGN KEY (user_id)
    REFERENCES users (id);

-- Relación Solicitud -> Afiliado (N-1)
ALTER TABLE credit_applications
    ADD CONSTRAINT fk_application_affiliate
    FOREIGN KEY (affiliate_id)
    REFERENCES affiliates (id);

-- Relación Solicitud -> Evaluación (1-1)
ALTER TABLE credit_applications
    ADD CONSTRAINT fk_application_risk_evaluation
    FOREIGN KEY (risk_evaluation_id)
    REFERENCES risk_evaluations (id);