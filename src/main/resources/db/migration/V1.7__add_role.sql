INSERT INTO respa.role (role_id, role_name, description, create_date)
VALUES (nextval('respa.role_seq'), 'ADMIN', 'Роль администратора системы', current_timestamp);