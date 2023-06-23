-- liquibase formatted sql

-- changeset weare4saken:1
CREATE TABLE IF NOT EXISTS driver
(
    id                  BIGSERIAL PRIMARY KEY,
    full_name           VARCHAR(80) NOT NULL,
    passport_data       VARCHAR(12) NOT NULL,
    license_category    VARCHAR(12) NOT NULL,
    birth_date          TIMESTAMP NOT NULL,
    experience          INT NOT NULL
);
