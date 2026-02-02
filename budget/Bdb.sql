CREATE DATABASE budget_db;
USE budget_db;

CREATE TABLE transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    amount DOUBLE,
    type VARCHAR(10),
    description VARCHAR(100),
    t_datetime DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE plans (
    id INT AUTO_INCREMENT PRIMARY KEY,
    item VARCHAR(50),
    planned_amount DOUBLE,
    priority INT,
    pursuing BOOLEAN,
    completed BOOLEAN
);

