package com.example.Hospital.Hospital.controller;

import com.example.Hospital.Hospital.entity.Medication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Hospital.Hospital.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@RestController
@RequestMapping("/medication")
public class MedicationController {
    @Autowired
    MedicationRepository medicineRepository;

    @PostMapping
    public ResponseEntity<Boolean> addMedicine(@RequestBody Medication medicine) {
        try {
            medicineRepository.save(medicine);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    @GetMapping
    public ResponseEntity<Iterable<Medication>> getMedicine() {
        return ResponseEntity.ok(medicineRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medication> getMedicineById(@PathVariable int id) {
        Optional<Medication> medicine = medicineRepository.findById(id);
        if (medicine.isPresent()) {
            return new ResponseEntity<>(medicine.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updateMedicine(@PathVariable int id, @RequestBody Medication medication) {
        Optional<Medication> med = medicineRepository.findById(id);

        if (med.isPresent()) {
            Medication medUpdate = med.get();

            if (medication.getName() != null) {
                medUpdate.setName(medication.getName());
            }
            if (medication.getStock() != 0) {
                medUpdate.setStock(medication.getStock());
            }
            if (medication.getDosage() != null) {
                medUpdate.setDosage(medication.getDosage());
            }
            if (medication.getAdminstrationRoute() != null) {
                medUpdate.setAdminstrationRoute(medication.getAdminstrationRoute());
            }

            try {
                medicineRepository.save(medUpdate);
                return new ResponseEntity<>(true, HttpStatus.OK);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
    }
}
