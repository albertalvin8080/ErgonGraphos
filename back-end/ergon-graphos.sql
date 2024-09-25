CREATE DATABASE IF NOT EXISTS ergon_graphos charset=utf8mb4 collate=utf8mb4_general_ci;

USE ergon_graphos;

CREATE TABLE IF NOT EXISTS sector
(
    sector_id   INT PRIMARY KEY AUTO_INCREMENT,
    sector_name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS employee
(
    employee_id        INT PRIMARY KEY AUTO_INCREMENT,
    employee_name      VARCHAR(50) NOT NULL,
    employee_sector_id INT         NOT NULL,
    CONSTRAINT fk_sector_id FOREIGN KEY (employee_sector_id) REFERENCES sector (sector_id)
);

CREATE TABLE IF NOT EXISTS problem
(
    problem_id          INT PRIMARY KEY AUTO_INCREMENT,
    problem_description VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS report
(
    report_id          INT PRIMARY KEY AUTO_INCREMENT,
    report_date        DATE NOT NULL,
    report_employee_id INT  NOT NULL,
    report_problem_id  INT  NOT NULL,
    CONSTRAINT fk_employee_id FOREIGN KEY (report_employee_id) REFERENCES employee (employee_id),
    CONSTRAINT fk_problem_id FOREIGN KEY (report_problem_id) REFERENCES problem (problem_id)
);

INSERT INTO problem (problem_description)
VALUES ('Equipment failure'),
       ('Lack of supplies'),
       ('Harassment'),
       ('Personnel failure'),
       ('Accident');

INSERT INTO sector (sector_name)
VALUES ('Printed Circuit Board Assembly'),
       ('Component Soldering'),
       ('Circuit Testing'),
       ('Casing'),
       ('Battery'),
       ('Screen and Display'),
       ('Software'),
       ('Quality Control')
;

INSERT INTO employee (employee_name, employee_sector_id)
VALUES ('Alyssa Kujo', 3),
       ('Franz Bonaparta', 1),
       ('Radagon Golden', 5),
       ('Johan Feige', 6),
       ('Godrick Grafted', 3),
       ('Nas`hrah', 4),
       ('Helmuth Voss', 2),
       ('Viktor Dragunov', 7),
       ('Katherine Belova', 8),
       ('Sergei Ivanov', 3),
       ('Elena Petrov', 5),
       ('Marcus Wright', 6),
       ('Sophia Van Helsing', 2),
       ('Jonathan Reed', 4),
       ('Freya Coldwell', 1)
;

INSERT INTO report (report_date, report_employee_id, report_problem_id)
VALUES ('2024-09-01', 1, 1),
       ('2024-09-01', 2, 2),
       ('2024-09-02', 3, 3),
       ('2024-09-02', 4, 4),
       ('2024-09-03', 5, 5),
       ('2024-09-03', 6, 1),
       ('2024-09-04', 1, 2),
       ('2024-09-04', 2, 3),
       ('2024-09-04', 3, 4),
       ('2024-09-05', 4, 5),
       ('2024-09-06', 7, 1),
       ('2024-09-06', 8, 3),
       ('2024-09-06', 9, 2),
       ('2024-09-06', 10, 4),
       ('2024-09-06', 11, 5),
       ('2024-09-06', 12, 1),
       ('2024-09-07', 13, 2),
       ('2024-09-07', 14, 4),
       ('2024-09-07', 1, 5),
       ('2024-09-07', 2, 1),
       ('2024-09-08', 3, 2),
       ('2024-09-08', 4, 3),
       ('2023-12-31', 5, 4),
       ('2024-09-08', 6, 5),
       ('2024-09-08', 7, 1),
       ('2024-09-09', 8, 2),
       ('2024-09-09', 9, 3),
       ('2024-09-09', 10, 4),
       ('2024-09-09', 11, 5),
       ('2024-09-09', 12, 1),
       ('2024-09-10', 13, 2),
       ('2024-09-10', 14, 3),
       ('2024-09-10', 1, 5),
       ('2024-09-10', 2, 1)
;

-- Coinciding date, sector_id and problem_description reports:
INSERT INTO report (report_date, report_employee_id, report_problem_id)
VALUES ('2024-09-11', 1, 1),
       ('2024-09-11', 5, 1),
       ('2024-09-11', 9, 1),
       ('2024-09-12', 6, 2),
       ('2024-09-12', 12, 2),
       ('2024-09-13', 8, 4),
       ('2024-09-13', 14, 4),
       ('2024-09-14', 2, 3),
       ('2024-09-14', 14, 3),
       ('2024-09-15', 4, 5),
       ('2024-09-11', 1, 1),
       ('2024-09-11', 5, 1),
       ('2024-09-11', 9, 1),
       ('2024-09-11', 7, 1),
       ('2024-09-11', 10, 1),
       ('2024-09-11', 12, 1),
       ('2024-09-12', 6, 2),
       ('2024-09-12', 13, 2),
       ('2024-09-12', 8, 2),
       ('2024-09-12', 11, 2)
;

INSERT INTO employee (employee_name, employee_sector_id)
VALUES ('Cahara', 2),
       ('Ragnvaldr', 4),
       ('Darce', 7),
       ('Enki', 6),
       ('Levi', 8),
       ('Nas`hrah', 3),
       ('Rondel', 3),
       ('Marina', 5),
       ('O`saa', 2),
       ('Gro`Goroth', 2),
       ('Valteil', 1),
       ('Rher', 2),
       ('Gaunt Knight', 1),
       ('Knight Solaire', 5),
       ('Lord Gwyn', 6),
       ('Siegmeyer of Catarina', 7),
       ('Artorias the Abysswalker', 7),
       ('Ciaran', 4),
       ('Gwyndolin', 4),
       ('Oscar of Astora', 6),
       ('Black Iron Tarkus', 2),
       ('Velka', 4),
       ('Quelaag', 5),
       ('Patches', 1),
       ('Crestfallen Warrior', 3),
       ('Garl Vinland', 2),
       ('Yurt, the Silent Chief', 3),
       ('Maiden Astraea', 4),
       ('Aldrich', 4),
       ('Pontiff Sulyvahn', 6)
;

INSERT INTO report (report_date, report_employee_id, report_problem_id)
VALUES ('2024-09-01', 15, 2),
       ('2024-09-01', 16, 1),
       ('2024-09-02', 17, 3),
       ('2024-09-02', 18, 4),
       ('2024-09-03', 19, 5),
       ('2024-09-03', 20, 1),
       ('2024-09-04', 21, 2),
       ('2024-09-04', 22, 3),
       ('2024-09-04', 23, 4),
       ('2024-09-05', 24, 5),
       ('2024-09-06', 25, 1),
       ('2024-09-06', 26, 2),
       ('2024-09-06', 27, 3),
       ('2024-09-06', 28, 4),
       ('2024-09-06', 29, 5),
       ('2024-09-06', 30, 1),
       ('2024-09-07', 31, 2),
       ('2024-09-07', 32, 3),
       ('2024-09-07', 33, 4),
       ('2024-09-07', 34, 5),
       ('2024-09-08', 35, 1),
       ('2024-09-08', 36, 2),
       ('2024-09-08', 37, 3),
       ('2024-09-08', 38, 4),
       ('2024-09-09', 39, 5),
       ('2024-09-09', 40, 1),
       ('2024-09-09', 41, 2),
       ('2024-09-09', 42, 3),
       ('2024-09-09', 43, 4),
       ('2024-09-09', 44, 5),
       ('2024-09-10', 45, 1),
       ('2024-09-11', 15, 2),
       ('2024-09-11', 16, 3),
       ('2024-09-11', 17, 4),
       ('2024-09-11', 18, 5),
       ('2024-09-11', 19, 1),
       ('2024-09-12', 20, 2),
       ('2024-09-12', 21, 3),
       ('2024-09-12', 22, 4),
       ('2024-09-12', 23, 5),
       ('2024-09-12', 24, 1),
       ('2024-09-13', 25, 2),
       ('2024-09-13', 26, 3),
       ('2024-09-13', 27, 4),
       ('2024-09-13', 28, 5),
       ('2024-09-13', 29, 1),
       ('2024-09-14', 30, 2),
       ('2024-09-14', 31, 3),
       ('2024-09-14', 32, 4),
       ('2024-09-14', 33, 5),
       ('2024-09-15', 34, 1),
       ('2024-09-15', 35, 2),
       ('2024-09-15', 36, 3),
       ('2024-09-15', 37, 4),
       ('2024-09-15', 38, 5),
       ('2024-09-16', 39, 1),
       ('2024-09-16', 40, 2),
       ('2024-09-16', 41, 3),
       ('2024-09-16', 42, 4),
       ('2024-09-16', 43, 5),
       ('2024-09-16', 44, 1)
;