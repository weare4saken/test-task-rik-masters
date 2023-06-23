-- liquibase formatted sql

-- changeset weare4saken:3
CREATE TABLE IF NOT EXISTS detail
(
    id              BIGSERIAL PRIMARY KEY,
    serial_number   VARCHAR(255) NOT NULL,
    type            VARCHAR(255) NOT NULL,
    car_vin         VARCHAR(17) REFERENCES car(vin)
);