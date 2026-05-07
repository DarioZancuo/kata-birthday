DROP TABLE IF EXISTS persons;

CREATE TABLE persons (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    surname VARCHAR(255),
    name VARCHAR(255),
    birth_date DATE,
    email VARCHAR(255),
    phone_number CHAR(10)
);