package com.example.Hospital.Hospital.controller;

import com.example.Hospital.Hospital.entity.DietTextureType;
import com.example.Hospital.Hospital.entity.DietType;
import com.example.Hospital.Hospital.repository.DietRepository;
import com.example.Hospital.Hospital.repository.DietTextureTypeRepository;
import com.example.Hospital.Hospital.repository.DietTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diet")
public class DietController {
    @Autowired
    private DietRepository dietRepository;
    @Autowired
    private DietTypeRepository dietTypeRepository;
    @Autowired
    private DietTextureTypeRepository dietTextureTypeRepository;

    @PostMapping("/type")
    public @ResponseBody ResponseEntity<Boolean> createDietType(@RequestBody List<DietType> dietType) {
        try {
            dietTypeRepository.saveAll(dietType);
            return ResponseEntity.status(HttpStatus.CREATED).body(true);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(false);
        }
    }

    @PostMapping("/texture")
    public @ResponseBody ResponseEntity<Boolean> createDietTexture(@RequestBody List<DietTextureType> texture) {
        try {
            dietTextureTypeRepository.saveAll(texture);
            return ResponseEntity.status(HttpStatus.CREATED).body(true);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(false);
        }
    }

    @GetMapping("/type")
    public @ResponseBody ResponseEntity<Iterable<DietType>> getAllDietType() {
        return ResponseEntity.ok(dietTypeRepository.findAll());
    }

    @GetMapping("/texture")
    public @ResponseBody ResponseEntity<Iterable<DietTextureType>> getAllDietTexture() {
        return ResponseEntity.ok(dietTextureTypeRepository.findAll());
    }

}
