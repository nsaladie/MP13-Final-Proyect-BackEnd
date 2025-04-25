package com.example.Hospital.Hospital.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.Hospital.Hospital.entity.DietTextureType;

public interface DietTextureTypeRepository extends CrudRepository<DietTextureType, Integer> {
	DietTextureType findByDescription(String description);
}