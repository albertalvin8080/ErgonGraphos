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
VALUES ('Printed Circuit Board (PCB) assembly'),
       ('Component Soldering'),
       ('Circuit Testing'),
       ('Casing'),
       ('Battery'),
       ('Screen and Display'),
       ('Software'),
       ('Quality Control')
;

INSERT INTO employee (employee_name, employee_sector_id)
VALUES ('Alya Kujo', 3),
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