package com.example.Hospital.Hospital.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.Hospital.Hospital.entity.Patient;
import com.example.Hospital.Hospital.entity.PatientRoomAssignment;
import com.example.Hospital.Hospital.entity.Room;

public interface PatientRoomAssignmentRepository extends CrudRepository<PatientRoomAssignment, Integer> {

	boolean existsByRoomAndActiveTrue(Room room);

	List<PatientRoomAssignment> findByRoomAndActiveTrue(Room room);

	Optional<PatientRoomAssignment> findByRoomAndPatientAndActiveTrue(Room room, Patient patient);

	Optional<PatientRoomAssignment> findByPatientHistorialNumberAndActiveTrue(Integer historialNumber);

}
