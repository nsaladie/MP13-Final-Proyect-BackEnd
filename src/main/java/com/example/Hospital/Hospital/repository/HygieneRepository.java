package com.example.Hospital.Hospital.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.Hospital.Hospital.entity.HygieneType;

public interface HygieneRepository extends CrudRepository<HygieneType, Integer> {

}
