package com.example.Hospital.Hospital.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DietTextureType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String textureTypeDesc;

	public DietTextureType() {
		super();
	}

	public DietTextureType(Integer id, String textureTypeDesc) {
		super();
		this.id = id;
		this.textureTypeDesc = textureTypeDesc;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTextureTypeDesc() {
		return textureTypeDesc;
	}

	public void setTextureTypeDesc(String textureTypeDesc) {
		this.textureTypeDesc = textureTypeDesc;
	}

	@Override
	public String toString() {
		return "DietTextureType [id=" + id+ ", textureTypeDesc=" + textureTypeDesc + "]";
	}

}
