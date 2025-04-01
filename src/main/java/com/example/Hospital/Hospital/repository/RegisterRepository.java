package com.example.Hospital.Hospital.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.Hospital.Hospital.entity.Register;

public interface RegisterRepository extends CrudRepository<Register, Integer> {

	List<Register> findByPatientHistorialNumberOrderByDateDesc(int patientId);

	Optional<Register> findTopByPatientHistorialNumberAndDiagnosisIsNotNullOrderByDateDesc(int historialNumber);

    Optional<Register> findByVitalSignId(int vitalSignId);

}
