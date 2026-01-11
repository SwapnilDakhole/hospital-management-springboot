package com.project.hospitalManagement.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Length;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime appointmentTime;

    @Column(length = 50)
    private String reason;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)      // owning side
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)       // owning side
    private Doctor doctor;
}
