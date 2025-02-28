package com.example.Hospital.Hospital.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DietType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer typeDietId;
	private String typeDietDesc;
	
	public DietType(Integer typeDietId, String typeDietDesc) {
		super();
		this.typeDietId = typeDietId;
		this.typeDietDesc = typeDietDesc;
	}

	public DietType() {
		
	}


	public Integer getTypeDietId() {
		return typeDietId;
	}



	public void setTypeDietId(Integer typeDietId) {
		this.typeDietId = typeDietId;
	}



	public String getTypeDietDesc() {
		return typeDietDesc;
	}



	public void setTypeDietDesc(String typeDietDesc) {
		this.typeDietDesc = typeDietDesc;
	}



	@Override
	public String toString() {
		return "DietType [typeDietId=" + typeDietId + ", typeDietDesc=" + typeDietDesc + "]";
	}
	
	

}
