package com.example.Hospital.Hospital.entity;

import jakarta.persistence.*;

@Entity
public class Room {
	@Id
	@Column(nullable = false, length = 7)
	private String roomId;
	private String observation;

	public Room() {
		super();
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", observation=" + observation + "]";
	}
}
