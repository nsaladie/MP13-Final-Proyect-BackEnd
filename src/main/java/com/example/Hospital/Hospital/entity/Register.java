package com.example.Hospital.Hospital.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

@Entity
public class Register {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "auxiliarId")
	private Auxiliary auxiliary;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private Date date;
	@ManyToOne
	@JoinColumn(name = "patientId")
	private Patient patient;
	@ManyToOne
	@JoinColumn(name = "higieneTypeId")
	private HygieneType hygieneType;
	private String observation;
	@OneToOne
	@JoinColumn(name = "dietId")
	private Diet diet;
	@OneToOne
	@JoinColumn(name = "drainId")
	private Drain drain;
	@OneToOne
	@JoinColumn(name = "mobilizationId")
	private Mobilization mobilization;
	@OneToOne
	@JoinColumn(name = "diagnosisId")
	private Diagnosis diagnosis;
	@OneToOne
	@JoinColumn(name = "vitalSignId")
	private VitalSign vitalSign;

	public Register() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Auxiliary getAuxiliary() {
		return auxiliary;
	}

	public void setAuxiliary(Auxiliary auxiliary) {
		this.auxiliary = auxiliary;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public HygieneType getHygieneType() {
		return hygieneType;
	}

	public void setHygieneType(HygieneType hygieneType) {
		this.hygieneType = hygieneType;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Diet getDiet() {
		return diet;
	}

	public void setDiet(Diet diet) {
		this.diet = diet;
	}

	public Drain getDrain() {
		return drain;
	}

	public void setDrain(Drain drain) {
		this.drain = drain;
	}

	public Mobilization getMobilization() {
		return mobilization;
	}

	public void setMobilization(Mobilization mobilization) {
		this.mobilization = mobilization;
	}

	public Diagnosis getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(Diagnosis diagnosis) {
		this.diagnosis = diagnosis;
	}

	public VitalSign getVitalSign() {
		return vitalSign;
	}

	public void setVitalSign(VitalSign vitalSign) {
		this.vitalSign = vitalSign;
	}

	@Override
	public String toString() {
		return "Register [id=" + id + ", auxiliary=" + auxiliary + ", date=" + date + ", patient=" + patient
				+ ", hygieneType=" + hygieneType + ", observation=" + observation + ", diet=" + diet + ", drain="
				+ drain + ", mobilization=" + mobilization + ", diagnosis=" + diagnosis + ", vitalSign=" + vitalSign
				+ "]";
	}

}
