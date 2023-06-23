-- liquibase formatted sql

-- changeset weare4saken:2
CREATE TABLE IF NOT EXISTS car
(
    vin                 VARCHAR(17) PRIMARY KEY,
    license_plate       VARCHAR(6) NOT NULL,
    producer            VARCHAR(50) NOT NULL,
    model               VARCHAR(25) NOT NULL,
    production_year     INT NOT NULL,
    driver_id           BIGINT REFERENCES driver(id)
);