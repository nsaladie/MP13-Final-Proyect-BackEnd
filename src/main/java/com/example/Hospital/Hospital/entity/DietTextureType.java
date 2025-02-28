package com.example.Hospital.Hospital.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DietTextureType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer typeTextureId;
	private String typeTextureDesc;
	
	public DietTextureType(Integer typeTextureId, String typeTextureDesc) {
		super();
		this.typeTextureId = typeTextureId;
		this.typeTextureDesc = typeTextureDesc;
	}
	
	public DietTextureType() {
		
	}

	public Integer getTypeTextureId() {
		return typeTextureId;
	}

	public void setTypeTextureId(Integer typeTextureId) {
		this.typeTextureId = typeTextureId;
	}

	public String getTypeTextureDesc() {
		return typeTextureDesc;
	}

	public void setTypeTextureDesc(String typeTextureDesc) {
		this.typeTextureDesc = typeTextureDesc;
	}

	@Override
	public String toString() {
		return "DietTextureType [typeTextureId=" + typeTextureId + ", typeTextureDesc=" + typeTextureDesc + "]";
	}
	
	
	
	


	

}
