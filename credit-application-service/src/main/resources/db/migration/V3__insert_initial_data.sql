

-- Hash válido para 'password': $2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy

INSERT INTO users (username, password) VALUES
('admin', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy'),
('analista', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy'),
('afiliado', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy');

-- Asignar Roles
-- Admin
INSERT INTO user_roles (user_id, role)
SELECT id, 'ROLE_ADMIN' FROM users WHERE username = 'admin';

-- Analista
INSERT INTO user_roles (user_id, role)
SELECT id, 'ROLE_ANALISTA' FROM users WHERE username = 'analista';

-- Afiliado
INSERT INTO user_roles (user_id, role)
SELECT id, 'ROLE_AFILIADO' FROM users WHERE username = 'afiliado';

-- Insertar un Afiliado de prueba
INSERT INTO affiliates (document, name, salary, affiliation_date, status) VALUES
('1017654311', 'Juan Perez', 5000000.00, '2023-01-01', 'ACTIVE');