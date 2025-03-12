package com.example.Hospital.Hospital.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
public class Diet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer dietId;
	private Integer dietDate;
	private String dietTakeData;
	@ManyToMany(mappedBy = "diets")
	private Set<DietType> dietTypes = new HashSet<>();
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "textureTypeId")
	private DietTextureType dietTypeTexture;
	private Integer dietIndependent;
	private Integer dietProsthesis;
	
	

	public Diet() {
		super();
	}

	public Diet(Integer dietId, Integer dietDate, String dietTakeData, Set<DietType> dietTypes,
			DietTextureType dietTypeTexture, Integer dietIndependent, Integer dietProsthesis) {
		super();
		this.dietId = dietId;
		this.dietDate = dietDate;
		this.dietTakeData = dietTakeData;
		this.dietTypes = dietTypes;
		this.dietTypeTexture = dietTypeTexture;
		this.dietIndependent = dietIndependent;
		this.dietProsthesis = dietProsthesis;
	}

	public Integer getDietId() {
		return dietId;
	}

	public void setDietId(Integer dietId) {
		this.dietId = dietId;
	}

	public Integer getDietDate() {
		return dietDate;
	}

	public void setDietDate(Integer dietDate) {
		this.dietDate = dietDate;
	}

	public String getDietTakeData() {
		return dietTakeData;
	}

	public void setDietTakeData(String dietTakeData) {
		this.dietTakeData = dietTakeData;
	}

	public Set<DietType> getDietTypes() {
		return dietTypes;
	}

	public void setDietTypes(Set<DietType> dietTypes) {
		this.dietTypes = dietTypes;
	}

	public DietTextureType getDietTypeTexture() {
		return dietTypeTexture;
	}

	public void setDietTypeTexture(DietTextureType dietTypeTexture) {
		this.dietTypeTexture = dietTypeTexture;
	}

	public Integer getDietIndependent() {
		return dietIndependent;
	}

	public void setDietIndependent(Integer dietIndependent) {
		this.dietIndependent = dietIndependent;
	}

	public Integer getDietProsthesis() {
		return dietProsthesis;
	}

	public void setDietProsthesis(Integer dietProsthesis) {
		this.dietProsthesis = dietProsthesis;
	}

	@Override
	public String toString() {
		return "Diet [dietId=" + dietId + ", dietDate=" + dietDate + ", dietTakeData=" + dietTakeData + ", dietTypes="
				+ dietTypes + ", dietTypeTexture=" + dietTypeTexture + ", dietIndependent=" + dietIndependent
				+ ", dietProsthesis=" + dietProsthesis + "]";
	}
	
	

}
