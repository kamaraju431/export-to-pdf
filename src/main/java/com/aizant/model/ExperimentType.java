package com.aizant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name ="ExperimentType")
@Component
public class ExperimentType {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	private String name;
	private int aliquot;
	private double[] sample;

	/* --------------- Getter setters --------------------- */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAliquot() {
		return aliquot;
	}
	public void setAliquot(int aliquot) {
		this.aliquot = aliquot;
	}
	
	public double[] getSample() {
		return sample;
	}
	public void setSample(double[] sample) {
		this.sample = sample;
	}
	public void setId(String experimentId2) {
		// TODO Auto-generated method stub
		
	}

	

	

}
