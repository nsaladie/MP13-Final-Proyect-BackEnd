package com.example.Hospital.Hospital.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Auxiliary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private String surname;

	public Auxiliary() {
		super();
	}

	public Integer getAuxiliarId() {
		return id;
	}

	public void setAuxiliarId(Integer auxiliarId) {
		this.id = auxiliarId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "Auxiliar [id=" + id + ", name=" + name + ", surname=" + surname + "]";
	}

}
