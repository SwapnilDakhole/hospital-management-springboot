DROP TABLE IF EXISTS patient;

CREATE TABLE patient(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    birth_date DATE,
    email VARCHAR(255) NOT NULL UNIQUE,
    gender VARCHAR(255),
    created_at TIME NOT NULL DEFAULT CURRENT_TIME,
    blood_group VARCHAR(255),
    CONSTRAINT unique_patient_name_birthdate UNIQUE (name, birth_date)
);
