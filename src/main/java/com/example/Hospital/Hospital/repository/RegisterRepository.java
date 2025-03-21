package com.example.Hospital.Hospital.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.Hospital.Hospital.entity.Register;

public interface RegisterRepository extends CrudRepository<Register, Integer> {

	Optional<Register> findTopByPatientHistorialNumberAndDiagnosisIsNotNullOrderByDateDesc(int historialNumber);

}
