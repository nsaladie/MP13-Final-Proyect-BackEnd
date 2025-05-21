package com.example.Hospital.Hospital.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Hospital.Hospital.entity.Patient;
import com.example.Hospital.Hospital.entity.PatientRoomAssignment;
import com.example.Hospital.Hospital.entity.Register;
import com.example.Hospital.Hospital.entity.Room;
import com.example.Hospital.Hospital.entity.RoomDTO;
import com.example.Hospital.Hospital.entity.RoomWithObservation;
import com.example.Hospital.Hospital.repository.PatientRepository;
import com.example.Hospital.Hospital.repository.PatientRoomAssignmentRepository;
import com.example.Hospital.Hospital.repository.RegisterRepository;
import com.example.Hospital.Hospital.repository.RoomRepository;

@RestController
@RequestMapping("/room")
public class RoomController {
	@Autowired
	RoomRepository roomRepository;
	@Autowired
	RegisterRepository registerRepository;
	@Autowired
	PatientRepository patientRepository;
	@Autowired
	PatientRoomAssignmentRepository assignmentRepository;

	public RoomController() {
		super();
	}

	@PostMapping
	public ResponseEntity<Boolean> createRoom(@RequestBody Room data) {
		try {
			roomRepository.save(data);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
		}
	}

	@PostMapping("/list")
	public ResponseEntity<Boolean> createRooms(@RequestBody List<Room> rooms) {
		try {
			roomRepository.saveAll(rooms);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
		}
	}

	@GetMapping
	public ResponseEntity<List<RoomDTO>> getAllRoomsWithOccupancyStatus() {
		try {
			List<Room> allRooms = new ArrayList<>();
			roomRepository.findAll().forEach(allRooms::add);
			List<RoomDTO> roomDTOs = new ArrayList<>();

			for (Room room : allRooms) {
				RoomDTO dto = new RoomDTO(room);

				List<PatientRoomAssignment> activeAssignments = assignmentRepository.findByRoomAndActiveTrue(room);

				if (!activeAssignments.isEmpty()) {
					PatientRoomAssignment assignment = activeAssignments.get(0);
					Patient patient = assignment.getPatient();

					Optional<Register> lastRegister = registerRepository
							.findTopByPatientHistorialNumberAndObservationIsNotNullOrderByDateDesc(
									patient.getHistorialNumber());

					dto.setOccupied(true);
					dto.setPatient(patient);
					dto.setAssignmentId(assignment.getId());
					dto.setAssignmentDate(assignment.getAssignmentDate());
					dto.setReleaseDate(assignment.getReleaseDate());

					if (lastRegister.isPresent() && lastRegister.get().getObservation() != null) {
						dto.setLastObservation(lastRegister.get().getObservation());
					}
				} else {
					dto.setOccupied(false);
				}

				roomDTOs.add(dto);
			}

			return ResponseEntity.ok(roomDTOs);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PutMapping("/discharge")
	public ResponseEntity<Boolean> dischargePatient(@RequestBody Patient patient) {
		try {
			Optional<PatientRoomAssignment> assignmentOpt = assignmentRepository
					.findByPatientHistorialNumberAndActiveTrue(patient.getHistorialNumber());

			if (assignmentOpt.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
			}

			PatientRoomAssignment assignment = assignmentOpt.get();
			assignment.setActive(false);
			assignment.setReleaseDate(new Date());

			assignmentRepository.save(assignment);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		}
	}

	@PutMapping("/assign")
	public ResponseEntity<Boolean> assignPatientsToRooms(@RequestBody List<PatientRoomAssignment> assignments) {
		try {
			assignmentRepository.saveAll(assignments);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
		}
	}

}
