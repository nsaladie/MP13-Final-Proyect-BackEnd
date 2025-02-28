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
	private DietTextureType dietTypeTextureId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "typeDietId")
	private DietType dietType;
	private String dietAssistance;
	private String dietProsthesis;
	private String patientPacNumHistorial;

	public Diet(Integer dietId, String dietDate, String dietTakeData, DietTextureType dietTypeTextureId,
			DietType dietType, String dietAssistance, String dietProsthesis, String patientPacNumHistorial) {
		super();
		this.dietId = dietId;
		this.dietDate = dietDate;
		this.dietTakeData = dietTakeData;
		this.dietTypeTextureId = dietTypeTextureId;
		this.dietType = dietType;
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

	public DietTextureType getDietTypeTextureId() {
		return dietTypeTextureId;
	}

	public void setDietTypeTexture_Id(DietTextureType dietTypeTextureId) {
		this.dietTypeTextureId = dietTypeTextureId;
	}

	public DietType getDietType() {
		return dietType;
	}

	public void setDietType(DietType dietType) {
		this.dietType = dietType;
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
				+ ", dietTypeTextureId=" + dietTypeTextureId + ", dietType=" + dietType + ", dietAssistance="
				+ dietAssistance + ", dietProsthesis=" + dietProsthesis + ", patientPacNumHistorial="
				+ patientPacNumHistorial + "]";
	}
	

	
	
	
	
	
}
