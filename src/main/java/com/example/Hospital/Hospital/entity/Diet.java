package com.example.Hospital.Hospital.entity;

import jakarta.persistence.*;

@Entity
public class Diet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer dietId;
	private String dietDate;
	private String dietTakeData;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "typeTextureId")
	private DietTextureType dietTypeTexture_Id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "typeDietId")
	private String diet_Type;
	private String dietAssistance;
	private String dietProsthesis;
	private String patientPacNumHistorial;

	public Diet(Integer dietId, String dietDate, String dietTakeData, DietTextureType dietTypeTexture_Id,
			String diet_Type, String dietAssistance, String dietProsthesis, String patientPacNumHistorial) {
		super();
		this.dietId = dietId;
		this.dietDate = dietDate;
		this.dietTakeData = dietTakeData;
		this.dietTypeTexture_Id = dietTypeTexture_Id;
		this.diet_Type = diet_Type;
		this.dietAssistance = dietAssistance;
		this.dietProsthesis = dietProsthesis;
		this.patientPacNumHistorial = patientPacNumHistorial;
	}
	
	public Diet()
	{
		
	}
	public Integer getDietId() {
		return dietId;
	}

	public void setDietId(Integer dietId) {
		this.dietId = dietId;
	}

	public String getDietDate() {
		return dietDate;
	}

	public void setDietDate(String dietDate) {
		this.dietDate = dietDate;
	}

	public String getDietTakeData() {
		return dietTakeData;
	}

	public void setDietTakeData(String dietTakeData) {
		this.dietTakeData = dietTakeData;
	}

	public DietTextureType getDietTypeTexture_Id() {
		return dietTypeTexture_Id;
	}

	public void setDietTypeTexture_Id(DietTextureType dietTypeTexture_Id) {
		this.dietTypeTexture_Id = dietTypeTexture_Id;
	}

	public String getDiet_Type() {
		return diet_Type;
	}

	public void setDiet_Type(String diet_Type) {
		this.diet_Type = diet_Type;
	}

	public String getDietAssistance() {
		return dietAssistance;
	}

	public void setDietAssistance(String dietAssistance) {
		this.dietAssistance = dietAssistance;
	}

	public String getDietProsthesis() {
		return dietProsthesis;
	}

	public void setDietProsthesis(String dietProsthesis) {
		this.dietProsthesis = dietProsthesis;
	}

	public String getPatientPacNumHistorial() {
		return patientPacNumHistorial;
	}

	public void setPatientPacNumHistorial(String patientPacNumHistorial) {
		this.patientPacNumHistorial = patientPacNumHistorial;
	}

	
	@Override
	public String toString() {
		return "Diet [dietId=" + dietId + ", dietDate=" + dietDate + ", dietTakeData=" + dietTakeData
				+ ", dietTypeTexture_Id=" + dietTypeTexture_Id + ", diet_Type=" + diet_Type + ", dietAssistance="
				+ dietAssistance + ", dietProsthesis=" + dietProsthesis + ", patientPacNumHistorial="
				+ patientPacNumHistorial + "]";
	}
	

	
	
	
	
	
}
