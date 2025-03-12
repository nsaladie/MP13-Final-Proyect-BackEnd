package com.example.Hospital.Hospital.entity;

import jakarta.persistence.*;

@Entity
public class VitalSign {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Double systolicBloodPressure;
	private Double diastolicBloodPressure;
	private Double respiratoryRate;
	private Double pulse;
	private Double temperature;
	private Double oxygenSaturation;
	private Double urineVolume;
	private Double bowelMovements;
	private Double serumTherapy;

	public VitalSign() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getSystolicBloodPressure() {
		return systolicBloodPressure;
	}

	public void setSystolicBloodPressure(Double systolicBloodPressure) {
		this.systolicBloodPressure = systolicBloodPressure;
	}

	public Double getDiastolicBloodPressure() {
		return diastolicBloodPressure;
	}

	public void setDiastolicBloodPressure(Double diastolicBloodPressure) {
		this.diastolicBloodPressure = diastolicBloodPressure;
	}

	public Double getRespiratoryRate() {
		return respiratoryRate;
	}

	public void setRespiratoryRate(Double respiratoryRate) {
		this.respiratoryRate = respiratoryRate;
	}

	public Double getPulse() {
		return pulse;
	}

	public void setPulse(Double pulse) {
		this.pulse = pulse;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public Double getOxygenSaturation() {
		return oxygenSaturation;
	}

	public void setOxygenSaturation(Double oxygenSaturation) {
		this.oxygenSaturation = oxygenSaturation;
	}

	public Double getUrineVolume() {
		return urineVolume;
	}

	public void setUrineVolume(Double urineVolume) {
		this.urineVolume = urineVolume;
	}

	public Double getBowelMovements() {
		return bowelMovements;
	}

	public void setBowelMovements(Double bowelMovements) {
		this.bowelMovements = bowelMovements;
	}

	public Double getSerumTherapy() {
		return serumTherapy;
	}

	public void setSerumTherapy(Double serumTherapy) {
		this.serumTherapy = serumTherapy;
	}

}
