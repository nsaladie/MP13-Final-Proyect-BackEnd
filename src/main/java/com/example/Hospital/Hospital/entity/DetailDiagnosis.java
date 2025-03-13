package com.example.Hospital.Hospital.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class DetailDiagnosis {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String dependencyLevel;
	private int oxygenLevel;
	@Column(nullable = true)
	private String oxygenLevelDescription;
	@Column(nullable = true)
	private boolean diapers;
	@Column(nullable = true)
	private int totalChangesDiapers;
	@Column(nullable = true)
	private String detailDescription;
	@Column(nullable = true)
	private String urinaryCatheter;
	@Column(nullable = true)
	private String rectalCatheter;
	@Column(nullable = true)
	private String nasogastricTube;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "diagnosisId")
	private Diagnosis diagnosis;

	public DetailDiagnosis() {
		super();
	}

	public Diagnosis getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(Diagnosis diagnosis) {
		this.diagnosis = diagnosis;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDependencyLevel() {
		return dependencyLevel;
	}

	public void setDependencyLevel(String dependencyLevel) {
		this.dependencyLevel = dependencyLevel;
	}

	public int getOxygenLevel() {
		return oxygenLevel;
	}

	public void setOxygenLevel(int oxygenLevel) {
		this.oxygenLevel = oxygenLevel;
	}

	public String getOxygenLevelDescription() {
		return oxygenLevelDescription;
	}

	public void setOxygenLevelDescription(String oxygenLevelDescription) {
		this.oxygenLevelDescription = oxygenLevelDescription;
	}

	public boolean isDiapers() {
		return diapers;
	}

	public void setDiapers(boolean diapers) {
		this.diapers = diapers;
	}

	public int getTotalChangesDiapers() {
		return totalChangesDiapers;
	}

	public void setTotalChangesDiapers(int totalChangesDiapers) {
		this.totalChangesDiapers = totalChangesDiapers;
	}

	public String getDetailDescription() {
		return detailDescription;
	}

	public void setDetailDescription(String detailDescription) {
		this.detailDescription = detailDescription;
	}

	public String getUrinaryCatheter() {
		return urinaryCatheter;
	}

	public void setUrinaryCatheter(String urinaryCatheter) {
		this.urinaryCatheter = urinaryCatheter;
	}

	public String getRectalCatheter() {
		return rectalCatheter;
	}

	public void setRectalCatheter(String rectalCatheter) {
		this.rectalCatheter = rectalCatheter;
	}

	public String getNasogastricTube() {
		return nasogastricTube;
	}

	public void setNasogastricTube(String nasogastricTube) {
		this.nasogastricTube = nasogastricTube;
	}

}