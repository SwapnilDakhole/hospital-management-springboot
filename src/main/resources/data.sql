-- Insert dummy data in an order that respects dependencies

-- Insert into tables with no foreign keys first
INSERT INTO insurance (provider, policy_number, valid_until, created_at) VALUES
('Blue Cross', 'BC12345', '2025-12-31', CURRENT_TIMESTAMP),
('Aetna', 'AE67890', '2026-06-30', CURRENT_TIMESTAMP),
('Cigna', 'CI54321', '2024-11-15', CURRENT_TIMESTAMP);

INSERT INTO doctor (name, specialization, email) VALUES
('Dr. James Smith', 'Cardiology', 'james.smith@example.com'),
('Dr. Mary Johnson', 'Neurology', 'mary.johnson@example.com'),
('Dr. Robert Williams', 'Pediatrics', 'robert.williams@example.com');

-- Insert into tables that have foreign keys
INSERT INTO patient (name, birth_date, email, gender, created_at, blood_group, insurance_id) VALUES
('John Smith', '1985-02-20', 'john.smith@example.com', 'Male', CURRENT_TIME, 'A_POSITIVE', 1),
('Jane Doe', '1990-07-15', 'jane.doe@example.com', 'Female', CURRENT_TIME, 'O_NEGATIVE', 2),
('Peter Jones', '1978-11-30', 'peter.jones@example.com', 'Male', CURRENT_TIME, 'B_POSITIVE', 3);

INSERT INTO department (name, head_doctor_id) VALUES
('Cardiology', 1),
('Neurology', 2),
('Pediatrics', 3);

-- Insert into join/linking tables
INSERT INTO department_doctor (department_id, doctor_id) VALUES
(1, 1),
(2, 2),
(3, 3);

INSERT INTO appointment (appointment_time, reason, status, patient_id, doctor_id) VALUES
(CURRENT_TIMESTAMP + INTERVAL '1 day', 'Annual Checkup', 'Scheduled', 1, 1),
(CURRENT_TIMESTAMP + INTERVAL '2 day', 'Headache', 'Scheduled', 2, 2),
(CURRENT_TIMESTAMP + INTERVAL '3 day', 'Fever', 'Scheduled', 3, 3);
