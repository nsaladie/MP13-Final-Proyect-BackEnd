package com.example.Hospital.Hospital.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Hospital.Hospital.entity.Auxiliar;
import com.example.Hospital.Hospital.repository.AuxiliarRepository;

@RestController
@RequestMapping("/auxiliar")
public class AuxiliarController {
	@Autowired
	private AuxiliarRepository auxiliarRepository;

	public AuxiliarController() {
		super();
	}

	@PostMapping("/login")
	public @ResponseBody ResponseEntity<Boolean> login(@RequestBody Auxiliar auxiliarId) {
		// Search if the auxiliary id is in the database
		Optional<Auxiliar> auxiliar = auxiliarRepository.findById(auxiliarId.getAuxiliarId());

		// If exist return true
		if (auxiliar.isPresent()) {
			return ResponseEntity.ok(true);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);

	}

	@PostMapping("")
	public @ResponseBody ResponseEntity<Auxiliar> addAuxiliar(@RequestBody Auxiliar auxiliarData) {
		try {
			Auxiliar addAuxiliar = new Auxiliar();
			addAuxiliar.setName(auxiliarData.getName());
			addAuxiliar.setSurname(auxiliarData.getSurname());

			auxiliarRepository.save(addAuxiliar);
			return ResponseEntity.status(HttpStatus.CREATED).body(addAuxiliar);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Optional<Auxiliar>> getAuxiliar(@PathVariable int id) {
		Optional<Auxiliar> auxiliar = auxiliarRepository.findById(id);

		// If exist return all the data of the auxiliary
		if (auxiliar.isPresent()) {
			return ResponseEntity.ok(auxiliar);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	}

}
