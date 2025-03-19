package com.example.Hospital.Hospital.entity;

import java.util.Date;

import jakarta.persistence.*;

@Entity
public class Room {
	@Id
	@Column(nullable = false, length = 7)
	private String roomId;
	private int roomNumber;
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

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getTimeInRoom() {
		return timeInRoom;
	}

	public void setTimeInRoom(Date timeInRoom) {
		this.timeInRoom = timeInRoom;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", roomNumber=" + roomNumber + ", timeInRoom=" + timeInRoom + ", patient="
				+ patient + "]";
	}

}
