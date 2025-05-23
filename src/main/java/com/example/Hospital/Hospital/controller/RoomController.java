package com.example.Hospital.Hospital.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
			// 1. Retrieve all rooms
			List<Room> allRooms = new ArrayList<>();
			roomRepository.findAll().forEach(allRooms::add);

			// 2. Retrieve all active assignments (using JOINs to avoid N+1 issues)
			List<PatientRoomAssignment> activeAssignments = assignmentRepository
					.findAllActiveAssignmentsWithPatientAndRoom();

			// 3. Map active assignments by roomId
			Map<String, PatientRoomAssignment> roomAssignmentMap = new HashMap<>();
			for (PatientRoomAssignment a : activeAssignments) {
				roomAssignmentMap.put(a.getRoom().getRoomId(), a);
			}

			// 4. Retrieve latest observations for patients in bulk
			List<Object[]> latestObservations = registerRepository.findLatestObservationsForPatients();
			Map<Integer, String> patientObservationMap = new HashMap<>();
			for (Object[] row : latestObservations) {
				Integer patientId = (Integer) row[0];
				String obs = (String) row[1];
				patientObservationMap.put(patientId, obs);
			}

			// 5. Create list of DTOs
			List<RoomDTO> result = new ArrayList<>();
			for (Room room : allRooms) {
				RoomDTO dto = new RoomDTO();
				dto.setRoom(room);

				PatientRoomAssignment assignment = roomAssignmentMap.get(room.getRoomId());
				if (assignment != null) {
					Patient patient = assignment.getPatient();
					dto.setOccupied(true);
					dto.setPatient(patient);
					dto.setAssignmentId(assignment.getId());
					dto.setAssignmentDate(assignment.getAssignmentDate());
					dto.setReleaseDate(assignment.getReleaseDate());
					dto.setLastObservation(patientObservationMap.get(patient.getHistorialNumber()));
				} else {
					dto.setOccupied(false);
				}

				result.add(dto);
			}

			return ResponseEntity.ok(result);
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
	public ResponseEntity<Boolean> assignPatientsToRooms(@RequestBody RoomDTO roomDTO) {
		try {
			Room room = roomDTO.getRoom();
			Patient patient = roomDTO.getPatient();

			if (patient != null && patient.getHistorialNumber() != null && room != null && room.getRoomId() != null) {

				PatientRoomAssignment assignment = new PatientRoomAssignment();
				assignment.setActive(true);
				assignment.setPatient(patient);
				assignment.setRoom(room);
				assignment.setAssignmentDate(
						roomDTO.getAssignmentDate() != null ? roomDTO.getAssignmentDate() : new Date());

				if (roomDTO.getReleaseDate() != null) {
					assignment.setReleaseDate(roomDTO.getReleaseDate());
				}

				assignmentRepository.save(assignment);

				return ResponseEntity.ok(true);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
		}
	}

}
