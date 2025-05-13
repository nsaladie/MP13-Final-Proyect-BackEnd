package com.example.Hospital.Hospital.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class PatientMedication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String frequency;
    private LocalDate startDate;
    private LocalDate endDate;
    private String notes;

    @ManyToOne
    @JoinColumn(name = "medication_id")
    private Medication medication;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private Auxiliary createdBy;

}
