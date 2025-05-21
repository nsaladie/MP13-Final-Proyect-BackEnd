package com.example.Hospital.Hospital.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer historialNumber;
	private String name;
	private String surname;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dateBirth;
	private String direction;
	private String language;
	private String history;
	private String allergy;
	private String caragiverName;
	private String caragiverNumber;

	public Patient() {
		super();
	}

	public Integer getHistorialNumber() {
		return historialNumber;
	}

	public void setHistorialNumber(Integer historialNumber) {
		this.historialNumber = historialNumber;
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

	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getAllergy() {
		return allergy;
	}

	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}

	public String getCaragiverName() {
		return caragiverName;
	}

	public void setCaragiverName(String caragiverName) {
		this.caragiverName = caragiverName;
	}

	public String getCaragiverNumber() {
		return caragiverNumber;
	}

	public void setCaragiverNumber(String caragiverNumber) {
		this.caragiverNumber = caragiverNumber;
	}

}
