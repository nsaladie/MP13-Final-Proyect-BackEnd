package com.example.Hospital.Hospital.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Drain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String drainOutput;
	private String drainType;

	public Drain() {
		super();
	}

	public Drain(Integer id, String drainOutput, String drainType) {
		super();
		this.id = id;
		this.drainOutput = drainOutput;
		this.drainType = drainType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDrainOutput() {
		return drainOutput;
	}

	public void setDrainOutput(String drainOutput) {
		this.drainOutput = drainOutput;
	}

	public String getDrainType() {
		return drainType;
	}

	public void setDrainType(String drainType) {
		this.drainType = drainType;
	}

	@Override
	public String toString() {
		return "Drain [id=" + id + ", drainOutput=" + drainOutput + ", draintype=" + drainType + "]";
	}

}
