package com.example.Hospital.Hospital.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.Hospital.Hospital.entity.Diagnosis;

public interface DiagnosisRepository extends CrudRepository<Diagnosis, Integer> {

}
