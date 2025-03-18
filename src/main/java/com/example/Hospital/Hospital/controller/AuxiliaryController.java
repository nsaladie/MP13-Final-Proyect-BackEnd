package com.example.Hospital.Hospital.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Hospital.Hospital.entity.Auxiliary;
import com.example.Hospital.Hospital.repository.AuxiliaryRepository;

@RestController
@RequestMapping("/auxiliary")
public class AuxiliaryController {
	@Autowired
	private AuxiliaryRepository auxiliarRepository;

	public AuxiliaryController() {
		super();
	}

	@PostMapping("/login")
	public @ResponseBody ResponseEntity<Boolean> login(@RequestBody Auxiliary auxiliarId) {
		// Search if the auxiliary id is in the database
		Optional<Auxiliary> auxiliar = auxiliarRepository.findById(auxiliarId.getId());

		// If exist return true
		if (auxiliar.isPresent()) {
			return ResponseEntity.ok(true);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);

	}

	@PostMapping("")
	public @ResponseBody ResponseEntity<Auxiliary> addAuxiliar(@RequestBody Auxiliary auxiliarData) {
		try {
			Auxiliary addAuxiliar = new Auxiliary();
			addAuxiliar.setName(auxiliarData.getName());
			addAuxiliar.setSurname(auxiliarData.getSurname());

			auxiliarRepository.save(addAuxiliar);
			return ResponseEntity.status(HttpStatus.CREATED).body(addAuxiliar);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Optional<Auxiliary>> getAuxiliar(@PathVariable int id) {
		Optional<Auxiliary> auxiliar = auxiliarRepository.findById(id);

		// If exist return all the data of the auxiliary
		if (auxiliar.isPresent()) {
			return ResponseEntity.ok(auxiliar);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	}

}
