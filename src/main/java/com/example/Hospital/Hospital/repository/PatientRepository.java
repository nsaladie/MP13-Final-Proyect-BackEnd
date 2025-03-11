package com.example.Hospital.Hospital.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.Hospital.Hospital.entity.Patient;

public interface PatientRepository extends CrudRepository<Patient, Integer> {

}
