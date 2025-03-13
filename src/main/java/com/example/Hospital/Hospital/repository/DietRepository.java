package com.example.Hospital.Hospital.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.example.Hospital.Hospital.entity.Diet;
import com.example.Hospital.Hospital.entity.DietType;
import com.example.Hospital.Hospital.entity.Nurse;
import com.example.Hospital.Hospital.entity.DietTextureType;

public interface DietRepository extends CrudRepository<Diet, Integer> {

}
