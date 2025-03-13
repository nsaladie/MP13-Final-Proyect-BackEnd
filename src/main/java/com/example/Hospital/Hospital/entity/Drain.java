package com.example.Hospital.Hospital.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Drain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String drainOutput;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dranTypeId")
	private DrainType draintype;

	public Drain() {
		super();
	}

	public Drain(Integer id, String drainOutput, DrainType draintype) {
		super();
		this.id = id;
		this.drainOutput = drainOutput;
		this.draintype = draintype;
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

	public DrainType getDraintype() {
		return draintype;
	}

	public void setDraintype(DrainType draintype) {
		this.draintype = draintype;
	}

	@Override
	public String toString() {
		return "Drain [id=" + id + ", drainOutput=" + drainOutput + ", draintype=" + draintype + "]";
	}

}
