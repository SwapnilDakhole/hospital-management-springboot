package com.project.hospitalManagement.service;

import com.project.hospitalManagement.Entity.Appointment;
import com.project.hospitalManagement.Entity.Doctor;
import com.project.hospitalManagement.Entity.Patient;
import com.project.hospitalManagement.repository.AppointmentRepository;
import com.project.hospitalManagement.repository.DoctorRepository;
import com.project.hospitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional; // Correct import
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppointmentService {

    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;

    @Transactional
    public void createNewAppointment(Appointment appointment, Long patientId, Long doctorId){
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with ID"+patientId));

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with ID"+doctorId));

        if(appointment.getId() != null) throw new IllegalArgumentException("Appointment ID must be null for a new appointment");


        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        patient.getAppointments().add(appointment); // to maintain bidirectional consistency
        doctor.getAppointments().add(appointment); // to maintain bidirectional consistency

        appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment reAssignedAppointmentToAnotherDoctor(Long appointmentId, Long doctorId){
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found with ID: " + appointmentId));

        Doctor newDoctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with ID: " + doctorId));

        // Remove appointment from the old doctor's list for consistency
        if (appointment.getDoctor() != null) {
            appointment.getDoctor().getAppointments().remove(appointment);
        }

        appointment.setDoctor(newDoctor);  // this will automatically call the update, because it is dirty

        newDoctor.getAppointments().add(appointment); // just for bidirectional consistency

        return appointment;
    }
}
