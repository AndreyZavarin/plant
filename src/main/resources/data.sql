INSERT INTO app_user (login, password_hash, role, password_date)
VALUES ('admin', '$2a$10$qyfgcEh2RMZ0wU05wXFkJONfVZYdbbrl/lDgiNtklA5.J3nqyYlMK', 'ADMIN', now());

INSERT INTO app_user (login, password_hash, role, password_date)
VALUES ('user', '$2a$10$qyfgcEh2RMZ0wU05wXFkJONfVZYdbbrl/lDgiNtklA5.J3nqyYlMK', 'USER', now());

INSERT INTO client (birth_date, date_created, first_name, gender, last_name, middle_name)
VALUES (now(), now(), 'ALEXEY', 'MALE', 'BOZHEV', 'IGOREVICH')