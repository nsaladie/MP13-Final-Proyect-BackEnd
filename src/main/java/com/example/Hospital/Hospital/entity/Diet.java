package com.example.Hospital.Hospital.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

@Entity
public class Diet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dietDate;
	private String dietTakeData;
	@ManyToMany(mappedBy = "diets")
	private Set<DietType> dietTypes = new HashSet<>();
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "textureTypeId")
	private DietTextureType dietTypeTexture;
	private Integer independent;
	private Integer prosthesis;

	public Diet() {
		super();
	}

	public Diet(Integer id, Date dietDate, String dietTakeData, Set<DietType> dietTypes,
			DietTextureType dietTypeTexture, Integer independent, Integer prosthesis) {
		super();
		this.id = id;
		this.dietDate = dietDate;
		this.dietTakeData = dietTakeData;
		this.dietTypes = dietTypes;
		this.dietTypeTexture = dietTypeTexture;
		this.independent = independent;
		this.prosthesis = prosthesis;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDietDate() {
		return dietDate;
	}

	public void setDietDate(Date dietDate) {
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
		for (DietType dietType : dietTypes) {
			dietType.getDiets().add(this);
		}
	}

	public DietTextureType getDietTypeTexture() {
		return dietTypeTexture;
	}

	public void setDietTypeTexture(DietTextureType dietTypeTexture) {
		this.dietTypeTexture = dietTypeTexture;
	}

	public Integer getIndependent() {
		return independent;
	}

	public void setIndependent(Integer independent) {
		this.independent = independent;
	}

	public Integer getProsthesis() {
		return prosthesis;
	}

	public void setProsthesis(Integer prosthesis) {
		this.prosthesis = prosthesis;
	}

	@Override
	public String toString() {
		return "Diet [id=" + id + ", dietDate=" + dietDate + ", dietTakeData=" + dietTakeData + ", dietTypes="
				+ dietTypes + ", dietTypeTexture=" + dietTypeTexture + ", independent=" + independent + ", prosthesis="
				+ prosthesis + "]";
	}

}
