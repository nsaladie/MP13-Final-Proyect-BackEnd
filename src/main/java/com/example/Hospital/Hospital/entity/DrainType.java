package com.example.Hospital.Hospital.entity;

import jakarta.persistence.*;

@Entity
public class DrainType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String drainTypeDesc;

	public DrainType() {
		super();
	}

	public DrainType(Integer id, String drainTypeDesc) {
		super();
		this.id = id;
		this.drainTypeDesc = drainTypeDesc;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDrainTypeDesc() {
		return drainTypeDesc;
	}

	public void setDrainTypeDesc(String drainTypeDesc) {
		this.drainTypeDesc = drainTypeDesc;
	}

	@Override
	public String toString() {
		return "DrainType [id=" + id + ", drainTypeDesc=" + drainTypeDesc + "]";
	}

}
