package com.example.Hospital.Hospital.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Auxiliar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer auxiliarId;

	private String name;
	private String surname;

	public Auxiliar() {
		super();
	}

	public Integer getAuxiliarId() {
		return auxiliarId;
	}

	public void setAuxiliarId(Integer auxiliarId) {
		this.auxiliarId = auxiliarId;
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
		return "Auxiliar [auxiliarId=" + auxiliarId + ", name=" + name + ", surname=" + surname + "]";
	}

}
