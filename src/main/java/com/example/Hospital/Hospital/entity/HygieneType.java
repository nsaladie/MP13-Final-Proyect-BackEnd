package com.example.Hospital.Hospital.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class HygieneType {
	//
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer typeHygId;
	private String typeHigDesc;
	
	public HygieneType() {
		super();
	}
	public HygieneType(Integer typeHygId, String typeHigDesc) {
		super();
		this.typeHygId = typeHygId;
		this.typeHigDesc = typeHigDesc;
	}
	public Integer getTypeHygId() {
		return typeHygId;
	}
	public void setTypeHygId(Integer typeHygId) {
		this.typeHygId = typeHygId;
	}
	public String getTypeHigDesc() {
		return typeHigDesc;
	}
	public void setTypeHigDesc(String typeHigDesc) {
		this.typeHigDesc = typeHigDesc;
	}
	@Override
	public String toString() {
		return "HygieneType [typeHygId=" + typeHygId + ", typeHigDesc=" + typeHigDesc + "]";
	}
	
	

}
