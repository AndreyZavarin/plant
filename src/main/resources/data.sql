INSERT INTO app_user (id, login, password_hash, role, password_date)
VALUES (1, 'admin', '$2a$10$qyfgcEh2RMZ0wU05wXFkJONfVZYdbbrl/lDgiNtklA5.J3nqyYlMK', 'ADMIN', now());

INSERT INTO app_user (id, login, password_hash, role, password_date)
VALUES (2, 'user', '$2a$10$qyfgcEh2RMZ0wU05wXFkJONfVZYdbbrl/lDgiNtklA5.J3nqyYlMK', 'USER', now());

INSERT INTO app_user (id, login, password_hash, role, password_date)
VALUES (3, 'user_3', '$2a$10$qyfgcEh2RMZ0wU05wXFkJONfVZYdbbrl/lDgiNtklA5.J3nqyYlMK', 'USER', now());

INSERT INTO app_user (id, login, password_hash, role, password_date)
VALUES (4, 'user_4', '$2a$10$qyfgcEh2RMZ0wU05wXFkJONfVZYdbbrl/lDgiNtklA5.J3nqyYlMK', 'USER', now());

INSERT INTO app_user (id, login, password_hash, role, password_date)
VALUES (5, 'user_5', '$2a$10$qyfgcEh2RMZ0wU05wXFkJONfVZYdbbrl/lDgiNtklA5.J3nqyYlMK', 'USER', now());

INSERT INTO app_user (id, login, password_hash, role, password_date)
VALUES (6, 'user_6', '$2a$10$qyfgcEh2RMZ0wU05wXFkJONfVZYdbbrl/lDgiNtklA5.J3nqyYlMK', 'USER', now());

INSERT INTO app_user (id, login, password_hash, role, password_date)
VALUES (7, 'user_7', '$2a$10$qyfgcEh2RMZ0wU05wXFkJONfVZYdbbrl/lDgiNtklA5.J3nqyYlMK', 'USER', now());

INSERT INTO app_user (id, login, password_hash, role, password_date)
VALUES (8, 'user_8', '$2a$10$qyfgcEh2RMZ0wU05wXFkJONfVZYdbbrl/lDgiNtklA5.J3nqyYlMK', 'USER', now());

INSERT INTO app_user (id, login, password_hash, role, password_date)
VALUES (9, 'user_9', '$2a$10$qyfgcEh2RMZ0wU05wXFkJONfVZYdbbrl/lDgiNtklA5.J3nqyYlMK', 'USER', now());

INSERT INTO app_user (id, login, password_hash, role, password_date)
VALUES (10, 'user_10', '$2a$10$qyfgcEh2RMZ0wU05wXFkJONfVZYdbbrl/lDgiNtklA5.J3nqyYlMK', 'USER', now());

INSERT INTO client (id, birth_date, date_created, first_name, gender, last_name, middle_name)
VALUES (1, DATE '1996-09-26', now(), 'ALEXEY', 'MALE', 'BOZHEV', 'IGOREVICH');

INSERT INTO client (id, birth_date, date_created, first_name, gender, last_name, middle_name)
VALUES (2, DATE '1996-09-26', now(), 'ALEXEY', 'MALE', 'BOZHEV', 'IGOREVICH');

INSERT INTO client (id, birth_date, date_created, first_name, gender, last_name, middle_name)
VALUES (3, DATE '1996-09-26', now(), 'ALEXEY', 'MALE', 'BOZHEV', 'IGOREVICH');

INSERT INTO client (id, birth_date, date_created, first_name, gender, last_name, middle_name)
VALUES (4, DATE '1996-09-26', now(), 'ALEXEY', 'MALE', 'BOZHEV', 'IGOREVICH');

INSERT INTO client (id, birth_date, date_created, first_name, gender, last_name, middle_name)
VALUES (5, DATE '1996-09-26', now(), 'ALEXEY', 'MALE', 'BOZHEV', 'IGOREVICH');

INSERT INTO client (id, birth_date, date_created, first_name, gender, last_name, middle_name)
VALUES (6, DATE '1996-09-26', now(), 'ALEXEY', 'MALE', 'BOZHEV', 'IGOREVICH');

INSERT INTO client (id, birth_date, date_created, first_name, gender, last_name, middle_name)
VALUES (7, DATE '1996-09-26', now(), 'ALEXEY', 'MALE', 'BOZHEV', 'IGOREVICH');

INSERT INTO client (id, birth_date, date_created, first_name, gender, last_name, middle_name)
VALUES (8, DATE '1996-09-26', now(), 'ALEXEY', 'MALE', 'BOZHEV', 'IGOREVICH');

INSERT INTO client (id, birth_date, date_created, first_name, gender, last_name, middle_name)
VALUES (9, DATE '1996-09-26', now(), 'ALEXEY', 'MALE', 'BOZHEV', 'IGOREVICH');

INSERT INTO client (id, birth_date, date_created, first_name, gender, last_name, middle_name)
VALUES (10, DATE '1996-09-26', now(), 'ALEXEY', 'MALE', 'BOZHEV', 'IGOREVICH');

INSERT INTO tariff (id, name, type, cost, quantity, description, lifetime)
VALUES (1, 'Тестовый', 1, 666, 10, 'Тестовый тариф', 900000000);

INSERT INTO tariff (id, name, type, cost, quantity, description, lifetime)
VALUES (2, 'Обычный', 1, 777, 11, 'Обычный тариф', 900000000);

INSERT INTO subscription (id, state, start_date, end_date, quantity, client_id, tariff_id)
VALUES  (1, 0, now(), '2018-01-01', 10, 1, 1);