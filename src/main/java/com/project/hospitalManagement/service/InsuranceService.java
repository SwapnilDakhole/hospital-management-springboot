package com.project.hospitalManagement.service;

import com.project.hospitalManagement.Entity.Insurance;
import com.project.hospitalManagement.Entity.Patient;
import com.project.hospitalManagement.repository.InsuranceRepository;
import com.project.hospitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {
    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Patient assignedInsuranceToPatient(Insurance insurance, Long patientId){
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with ID: "+patientId));

        patient.setInsurance(insurance);

        insurance.setPatient(patient);
        patientRepository.save(patient);

        return patient;
    }

    @Transactional
    public Patient disassociateInsuranceFromPatient(Long patientId){
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with ID: "+patientId));

        patient.setInsurance(null);
        return patient;
    }
}
