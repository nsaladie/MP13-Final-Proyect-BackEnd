package com.example.Hospital.Hospital.repository;

import java.util.*;

import com.example.Hospital.Hospital.entity.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;

public interface RegisterRepository extends CrudRepository<Register, Integer> {

    Optional<Register> findByVitalSignId(int vitalSignId);

    List<Register> findByPatientHistorialNumberAndDiagnosisIsNotNullOrderByDateDesc(int patientId);

    Optional<Register> findTopByPatientHistorialNumberAndDiagnosisIsNotNullOrderByDateDesc(int historialNumber);

    @Query("SELECT r.vitalSign FROM Register r WHERE r.patient.historialNumber = :patientId AND r.vitalSign IS NOT NULL ORDER BY r.date DESC")
    List<VitalSign> findVitalSignsByPatientHistorialNumber(@Param("patientId") int patientId);

    @Query("SELECT r.patient.historialNumber AS id, r.observation AS obs FROM Register r " + "WHERE r.observation IS NOT NULL " + "AND r.date IN (SELECT MAX(r2.date) FROM Register r2 WHERE r2.observation IS NOT NULL GROUP BY r2.patient.historialNumber)")
    List<Object[]> findLatestObservationsForPatients();

}
