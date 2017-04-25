package com.aizant.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.stereotype.Component;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "BloodSampleCollection")
@Component
public class BloodSampleCollection {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	@Expose
	private int id;
	@Expose
	private String date;
	@Expose
	private double time;
	@Expose
	private int period;
	@Expose
	private int aliquot;
	@Expose
	private String scanTime;
	@Expose
	private String expectedTime;
	@Expose
	private String comments;
	@Expose
	private String registerNumber;
	@Expose
	private String scannedById;
	@Expose
	private String scannedByName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public String getScanTime() {
		return scanTime;
	}

	public void setScanTime(String scanTime) {
		this.scanTime = scanTime;
	}

	public String getExpectedTime() {
		return expectedTime;
	}
	
	public void setExpectedTime(String expectedTime) {
		this.expectedTime = expectedTime;
	}
	
	public int getAliquot() {
		return aliquot;
	}

	public void setAliquot(int aliquot) {
		this.aliquot = aliquot;
	}

	public String getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}
	
	public String getScannedById() {
		return this.getScannedById();
	}
	
	public void setScannedById(String scannedById) {
		this.scannedById = scannedById;
	}
	
	public String getScannedByName() {
		return this.scannedByName;
	}
	
	public void setScannedByName(String scannedByName) {
		this.scannedByName = scannedByName;
	}	
}
