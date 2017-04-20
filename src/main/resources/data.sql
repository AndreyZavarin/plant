INSERT INTO app_user (id, login, password_hash, role, password_date)
VALUES (1,'admin', '$2a$10$qyfgcEh2RMZ0wU05wXFkJONfVZYdbbrl/lDgiNtklA5.J3nqyYlMK', 'ADMIN', now());

INSERT INTO app_user (id, login, password_hash, role, password_date)
VALUES (2, 'user', '$2a$10$qyfgcEh2RMZ0wU05wXFkJONfVZYdbbrl/lDgiNtklA5.J3nqyYlMK', 'USER', now());

INSERT INTO client (id, birth_date, date_created, first_name, gender, last_name, middle_name)
VALUES (1, DATE '1996-09-26', now(), 'ALEXEY', 'MALE', 'BOZHEV', 'IGOREVICH');

INSERT INTO client (id, birth_date, date_created, first_name, gender, last_name, middle_name)
VALUES (2, DATE '1996-09-26', now(), 'ALEXEY', 'MALE', 'BOZHEV', 'IGOREVICH');

INSERT INTO tariff (id ,name, type, cost, quantity, description, lifetime)
VALUES (1, 'Тестовый', 1, 666, 10, 'Тестовый тариф', 900000000);

INSERT INTO tariff (id, name, type, cost, quantity, description, lifetime)
VALUES (2, 'Обычный', 1, 777, 11, 'Обычный тариф', 900000000);