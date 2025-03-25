package com.example.Hospital.Hospital.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class HygieneType {
	//
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String typeHigDesc;

	public HygieneType() {
		super();
	}

	public HygieneType(Integer id, String typeHigDesc) {
		super();
		this.id = id;
		this.typeHigDesc = typeHigDesc;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypeHigDesc() {
		return typeHigDesc;
	}

	public void setTypeHigDesc(String typeHigDesc) {
		this.typeHigDesc = typeHigDesc;
	}

	@Override
	public String toString() {
		return "HygieneType [id=" + id + ", typeHigDesc=" + typeHigDesc + "]";
	}

}
