package com.example.Hospital.Hospital.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.Hospital.Hospital.entity.Room;

public interface RoomRepository extends CrudRepository<Room, String> {

}
