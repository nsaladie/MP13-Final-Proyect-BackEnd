package com.example.Hospital.Hospital.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Hospital.Hospital.entity.Room;
import com.example.Hospital.Hospital.repository.RoomRepository;

@RestController
@RequestMapping("/room")
public class RoomController {
	@Autowired
	RoomRepository roomRepository;

	public RoomController() {
		super();
	}

	@PostMapping
	public ResponseEntity<Boolean> createRoom(@RequestBody Room data) {
		try {
			roomRepository.save(data);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
		}
	}

	@PostMapping("/list")
	public ResponseEntity<Boolean> createRooms(@RequestBody List<Room> rooms) {
		try {
			roomRepository.saveAll(rooms);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
		}
	}

	@GetMapping
	public @ResponseBody ResponseEntity<Iterable<Room>> getListRoom() {
		return ResponseEntity.ok((roomRepository.findAll()));
	}
}
