INSERT INTO app_user (login, password_hash, role, password_date)
VALUES ('admin', '$2a$10$qyfgcEh2RMZ0wU05wXFkJONfVZYdbbrl/lDgiNtklA5.J3nqyYlMK', 'ADMIN', now());

INSERT INTO app_user (login, password_hash, role, password_date)
VALUES ('user', '$2a$10$qyfgcEh2RMZ0wU05wXFkJONfVZYdbbrl/lDgiNtklA5.J3nqyYlMK', 'USER', now());

INSERT INTO client (birth_date, date_created, first_name, gender, last_name, middle_name)
VALUES (DATE '1996-09-26', now(), 'ALEXEY', 'MALE', 'BOZHEV', 'IGOREVICH');

INSERT INTO tariff (name, type, cost, quantity, description, lifetime)
VALUES ('Тестовый', 1, 666, 10, 'Тестовый тариф', 604800)