package com.example.Hospital.Hospital.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class DietType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String description;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "diet_dietType", joinColumns = @JoinColumn(name = "dietTypeId"), inverseJoinColumns = @JoinColumn(name = "dietId"))
	@JsonBackReference
	private Set<Diet> diets = new HashSet<>();

	public DietType() {
		super();
	}

	public DietType(Integer id, String description, Set<Diet> diets) {
		super();
		this.id = id;
		this.description = description;
		this.diets = diets;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Diet> getDiets() {
		return diets;
	}

	public void setDiets(Set<Diet> diets) {
		this.diets = diets;
	}

	@Override
	public String toString() {
		return "DietType [id=" + id + ", description=" + description + ", diets=" + diets + "]";
	}

}
