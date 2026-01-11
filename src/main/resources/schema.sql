-- Drop all tables, using CASCADE to handle dependencies automatically
DROP TABLE IF EXISTS appointment CASCADE;
DROP TABLE IF EXISTS department_doctor CASCADE;
DROP TABLE IF EXISTS department CASCADE;
DROP TABLE IF EXISTS doctor CASCADE;
DROP TABLE IF EXISTS patient CASCADE;
DROP TABLE IF EXISTS insurance CASCADE;

-- Create tables in an order that respects dependencies

-- Create insurance and doctor tables (no dependencies)
CREATE TABLE insurance (
    id BIGSERIAL PRIMARY KEY,
    provider VARCHAR(50),
    policy_number VARCHAR(50) UNIQUE,
    valid_until DATE NOT NULL,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE doctor (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    specialization VARCHAR(100),
    email VARCHAR(100) NOT NULL UNIQUE
);

-- Create patient and department tables (depend on insurance/doctor)
CREATE TABLE patient (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    birth_date DATE,
    email VARCHAR(255) NOT NULL UNIQUE,
    gender VARCHAR(255),
    created_at TIME NOT NULL,
    blood_group VARCHAR(255),
    insurance_id BIGINT,
    CONSTRAINT fk_patient_insurance FOREIGN KEY (insurance_id) REFERENCES insurance(id),
    CONSTRAINT unique_patient_name_birthdate UNIQUE (name, birth_date)
);

CREATE TABLE department (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    head_doctor_id BIGINT,
    CONSTRAINT fk_department_head_doctor FOREIGN KEY (head_doctor_id) REFERENCES doctor(id)
);

-- Create join/linking tables
CREATE TABLE department_doctor (
    department_id BIGINT NOT NULL,
    doctor_id BIGINT NOT NULL,
    PRIMARY KEY (department_id, doctor_id),
    CONSTRAINT fk_department_doctor_department FOREIGN KEY (department_id) REFERENCES department(id),
    CONSTRAINT fk_department_doctor_doctor FOREIGN KEY (doctor_id) REFERENCES doctor(id)
);

CREATE TABLE appointment (
    id BIGSERIAL PRIMARY KEY,
    appointment_time TIMESTAMP NOT NULL,
    reason VARCHAR(50),
    status VARCHAR(255) NOT NULL,
    patient_id BIGINT NOT NULL,
    doctor_id BIGINT NOT NULL,
    CONSTRAINT fk_appointment_patient FOREIGN KEY (patient_id) REFERENCES patient(id),
    CONSTRAINT fk_appointment_doctor FOREIGN KEY (doctor_id) REFERENCES doctor(id)
);
