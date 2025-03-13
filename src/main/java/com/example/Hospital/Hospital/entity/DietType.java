package com.example.Hospital.Hospital.entity;

import java.util.HashSet;
import java.util.Set;

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
	private Integer dietTypeId;
	private String dietTypeDesc;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "diet_dietType", joinColumns = @JoinColumn(name = "dietTypeId"), inverseJoinColumns = @JoinColumn(name = "dietId"))
	private Set<Diet> diets = new HashSet<>();

	public DietType() {
		super();
	}

	public DietType(Integer dietTypeId, String dietTypeDesc, Set<Diet> diets) {
		super();
		this.dietTypeId = dietTypeId;
		this.dietTypeDesc = dietTypeDesc;
		this.diets = diets;
	}

	public Integer getDietTypeId() {
		return dietTypeId;
	}

	public void setDietTypeId(Integer dietTypeId) {
		this.dietTypeId = dietTypeId;
	}

	public String getDietTypeDesc() {
		return dietTypeDesc;
	}

	public void setDietTypeDesc(String dietTypeDesc) {
		this.dietTypeDesc = dietTypeDesc;
	}

	public Set<Diet> getDiets() {
		return diets;
	}

	public void setDiets(Set<Diet> diets) {
		this.diets = diets;
	}

	@Override
	public String toString() {
		return "DietType [dietTypeId=" + dietTypeId + ", dietTypeDesc=" + dietTypeDesc + ", diets=" + diets + "]";
	}

}
