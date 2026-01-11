package com.project.hospitalManagement;

import com.project.hospitalManagement.Entity.Insurance;
import com.project.hospitalManagement.Entity.Patient;
import com.project.hospitalManagement.repository.PatientRepository;
import com.project.hospitalManagement.service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class InsuranceTest {
    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void testInsurance(){
        Insurance insurance = Insurance.builder()
                .policyNumber("HDFC_1234")
                .provider("HDFC")
                .validUntil(LocalDate.of(2030, 12, 12))
                .build();

        insuranceService.assignedInsuranceToPatient(insurance, 1L);
    }

    @Test
    public void testDisassociateInsurance(){
        Patient patient = insuranceService.disassociateInsuranceFromPatient(3L);
        assertNotNull(patient);
    }

}
