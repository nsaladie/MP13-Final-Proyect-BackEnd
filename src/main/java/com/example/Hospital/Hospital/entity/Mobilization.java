package com.example.Hospital.Hospital.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Mobilization {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer sedestation;
	private Integer walkingAssis;
	private String assisDesc;
	private String changes;
	private String decubitus;
	
	public Mobilization() {
		super();
	}
	public Mobilization(Integer id, Integer sedestation, Integer walkingAssis, String assisDesc, String changes,
			String decubitus) {
		super();
		this.id = id;
		this.sedestation = sedestation;
		this.walkingAssis = walkingAssis;
		this.assisDesc = assisDesc;
		this.changes = changes;
		this.decubitus = decubitus;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSedestation() {
		return sedestation;
	}
	public void setSedestation(Integer sedestation) {
		this.sedestation = sedestation;
	}
	public Integer getWalkingAssis() {
		return walkingAssis;
	}
	public void setWalkingAssis(Integer walkingAssis) {
		this.walkingAssis = walkingAssis;
	}
	public String getAssisDesc() {
		return assisDesc;
	}
	public void setAssisDesc(String assisDesc) {
		this.assisDesc = assisDesc;
	}
	public String getChanges() {
		return changes;
	}
	public void setChanges(String changes) {
		this.changes = changes;
	}
	public String getDecubitus() {
		return decubitus;
	}
	public void setDecubitus(String decubitus) {
		this.decubitus = decubitus;
	}
	@Override
	public String toString() {
		return "Mobilization [id=" + id + ", sedestation=" + sedestation + ", walkingAssis=" + walkingAssis
				+ ", assisDesc=" + assisDesc + ", changes=" + changes + ", decubitus=" + decubitus + "]";
	}
	
	
	

}
