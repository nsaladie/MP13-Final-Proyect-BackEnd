package com.example.Hospital.Hospital.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.Hospital.Hospital.entity.Register;

public interface RegisterRepository extends CrudRepository<Register, Integer> {

	List<Register> findByPatientHistorialNumberAndVitalSignIsNotNullOrderByDateDesc(int patientId);

	Optional<Register> findTopByPatientHistorialNumberAndDiagnosisIsNotNullOrderByDateDesc(int historialNumber);

	Optional<Register> findByVitalSignId(int vitalSignId);

	@Query("SELECT r.patient.historialNumber AS id, r.observation AS obs FROM Register r "
			+ "WHERE r.observation IS NOT NULL "
			+ "AND r.date IN (SELECT MAX(r2.date) FROM Register r2 WHERE r2.observation IS NOT NULL GROUP BY r2.patient.historialNumber)")
	List<Object[]> findLatestObservationsForPatients();

	List<Register> findByPatientHistorialNumberAndDiagnosisIsNotNullOrderByDateDesc(int patientId);

}
