package com.example.Hospital.Hospital.controller;

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

	@PostMapping("")
	public @ResponseBody ResponseEntity<Patient> addAuxiliar(@RequestBody Patient patientData) {
		try {
			Patient createPatient = new Patient();
			createPatient.setName(patientData.getName());
			createPatient.setSurname(patientData.getSurname());
			createPatient.setHistory(patientData.getHistory());
			createPatient.setAllergy(patientData.getAllergy());
			createPatient.setLanguage(patientData.getLanguage());
			createPatient.setDateBirth(patientData.getDateBirth());
			createPatient.setDirection(patientData.getDirection());
			createPatient.setDateEntry(patientData.getDateEntry());
			createPatient.setCaragiverName(patientData.getCaragiverName());
			createPatient.setCaragiverNumber(patientData.getCaragiverNumber());
			patientRepository.save(createPatient);
			return ResponseEntity.status(HttpStatus.CREATED).body(createPatient);
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

			if (update.getCaragiverName() != null) {
				updateData.setCaragiverName(update.getCaragiverName());
			}
			if (update.getCaragiverNumber() != null) {
				updateData.setCaragiverNumber(update.getCaragiverNumber());
			}
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
}
