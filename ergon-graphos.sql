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
    employee_sector_id INT          NOT NULL,
    CONSTRAINT fk_sector_id FOREIGN KEY (employee_sector_id) REFERENCES sector (sector_id)
);

CREATE TABLE IF NOT EXISTS problem
(
    problem_id          INT PRIMARY KEY AUTO_INCREMENT,
    problem_description VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS reportRepository
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
       ('Helmuth Voss', 2)
;