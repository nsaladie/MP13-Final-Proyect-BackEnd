package com.example.Hospital.Hospital.entity;

import java.util.Date;

import jakarta.persistence.*;

@Entity
public class Room {
	@Id
	@Column(nullable = false, length = 7)
	private String roomId;
	private String observation;
	@Column(name = "time_in_room", nullable = true)
	private Date timeInRoom;
	@OneToOne
	@JoinColumn(name = "patient_id", referencedColumnName = "historialNumber")
	private Patient patient;

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
		return "Room [roomId=" + roomId + ", observation=" + observation + ", timeInRoom=" + timeInRoom + ", patient="
				+ patient + "]";
	}

}
