package com.example.Hospital.Hospital.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DietTextureType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer textureTypeId;
	private String textureTypeDesc;

	public DietTextureType() {
		super();
	}

	public DietTextureType(Integer textureTypeId, String textureTypeDesc) {
		super();
		this.textureTypeId = textureTypeId;
		this.textureTypeDesc = textureTypeDesc;
	}

	public Integer getTextureTypeId() {
		return textureTypeId;
	}

	public void setTextureTypeId(Integer textureTypeId) {
		this.textureTypeId = textureTypeId;
	}

	public String getTextureTypeDesc() {
		return textureTypeDesc;
	}

	public void setTextureTypeDesc(String textureTypeDesc) {
		this.textureTypeDesc = textureTypeDesc;
	}

	@Override
	public String toString() {
		return "DietTextureType [textureTypeId=" + textureTypeId + ", textureTypeDesc=" + textureTypeDesc + "]";
	}

}
