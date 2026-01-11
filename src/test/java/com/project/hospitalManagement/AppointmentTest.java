package com.project.hospitalManagement;

import com.project.hospitalManagement.Entity.Appointment;
import com.project.hospitalManagement.Entity.Doctor;
import com.project.hospitalManagement.Entity.Patient;
import com.project.hospitalManagement.repository.DoctorRepository;
import com.project.hospitalManagement.repository.PatientRepository;
import com.project.hospitalManagement.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AppointmentTest {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;


    @Test
    public void testCreateAppointment(){
//        Patient patient = patientRepository.findById(1L).orElse(null);
//        Doctor doctor = doctorRepository.findById(1L).orElse(null);
        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.now())
                .reason("Fever")
                .status("Scheduled")
                .build();
        appointmentService.createNewAppointment(appointment, 1L, 1L);
        assertNotNull(appointment.getId());
    }

    @Test
    public void testReAssignAppointment(){
        Appointment appointment = appointmentService.reAssignedAppointmentToAnotherDoctor(1L, 2L);
        assertNotNull(appointment);
    }
}
