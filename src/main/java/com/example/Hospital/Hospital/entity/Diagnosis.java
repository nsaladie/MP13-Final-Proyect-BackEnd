package com.example.Hospital.Hospital.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
public class Diagnosis {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String description;
	@OneToMany(mappedBy = "diagnosis", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private Set<DetailDiagnosis> detailDiagnosisSet = new HashSet<>();

	public Diagnosis() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<DetailDiagnosis> getDetailDiagnosisSet() {
		return detailDiagnosisSet;
	}

	public void setDetailDiagnosisSet(Set<DetailDiagnosis> detailDiagnosisSet) {
		this.detailDiagnosisSet = detailDiagnosisSet;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
