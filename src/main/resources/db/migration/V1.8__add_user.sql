INSERT INTO respa.usr (user_id, login, password, status, last_update_date)
VALUES (nextval('respa.usr_seq'), 'respa_admin', '$2a$10$VA5jMwoc65bxnqPMmT7ldeOnh2bNLSZzpX3dpRn/jV25BNmlvEOtK',
        true, current_timestamp);

INSERT INTO respa.user_role (user_role_id, user_id, role_id)
VALUES (nextval('respa.user_role_seq'),
        (SELECT user_id FROM respa.usr WHERE login = 'respa_admin'),
        (SELECT role_id FROM respa.role WHERE role_name = 'ADMIN'));