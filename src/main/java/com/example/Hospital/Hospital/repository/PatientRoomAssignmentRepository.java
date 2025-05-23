package com.example.Hospital.Hospital.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.Hospital.Hospital.entity.Patient;
import com.example.Hospital.Hospital.entity.PatientRoomAssignment;
import com.example.Hospital.Hospital.entity.Room;

public interface PatientRoomAssignmentRepository extends CrudRepository<PatientRoomAssignment, Integer> {

	Optional<PatientRoomAssignment> findByPatientHistorialNumberAndActiveTrue(Integer historialNumber);

	@Query("SELECT a FROM PatientRoomAssignment a " + "JOIN FETCH a.patient p " + "JOIN FETCH a.room r "
			+ "WHERE a.active = true")
	List<PatientRoomAssignment> findAllActiveAssignmentsWithPatientAndRoom();

}
