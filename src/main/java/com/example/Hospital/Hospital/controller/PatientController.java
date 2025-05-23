package com.example.Hospital.Hospital.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.Hospital.Hospital.entity.Patient;
import com.example.Hospital.Hospital.repository.PatientRepository;

@RestController
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	private PatientRepository patientRepository;

	@PostMapping("/list")
	public ResponseEntity<Boolean> addPatients(@RequestBody List<Patient> patients) {
		try {
			patientRepository.saveAll(patients);
			return ResponseEntity.status(HttpStatus.CREATED).body(true);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(false);
		}
	}

	@PostMapping()
	public ResponseEntity<Patient> addSinglePatient(@RequestBody Patient patient) {
		try {
			patientRepository.save(patient);
			return ResponseEntity.status(HttpStatus.CREATED).body(patient);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Optional<Patient>> getPatient(@PathVariable int id) {

		Optional<Patient> patient = patientRepository.findById(id);

		if (patient.isPresent()) {
			return ResponseEntity.ok(patient);
		}
		return ResponseEntity.badRequest().build();

	}

	@PutMapping("/{id}")
	public @ResponseBody ResponseEntity<Patient> updatePatient(@PathVariable int id, @RequestBody Patient update) {
		Optional<Patient> patient = patientRepository.findById(id);
		if (patient.isPresent()) {
			Patient updateData = patient.get();

			if (update.getAllergy() != null) {
				updateData.setAllergy(update.getAllergy());
			}
			if (update.getCaragiverName() != null) {
				updateData.setCaragiverName(update.getCaragiverName());
			}
			if (update.getCaragiverNumber() != null) {
				updateData.setCaragiverNumber(update.getCaragiverNumber());
			}
			try {
				patientRepository.save(updateData);
				return ResponseEntity.ok(updateData);
			} catch (Exception e) {
				return ResponseEntity.badRequest().build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/listAll")
	public @ResponseBody ResponseEntity<Iterable<Patient>> getAllPatients() {
		try {
			Iterable<Patient> patients = patientRepository.findAll();

			if (patients == null || !patients.iterator().hasNext()) {
				return ResponseEntity.ok(Collections.emptyList());
			}

			return ResponseEntity.ok(patients);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
		}
	}
}
