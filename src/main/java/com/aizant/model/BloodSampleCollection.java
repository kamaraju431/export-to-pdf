package com.aizant.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;
@Entity
@Table(name ="BloodSampleCollection")
@Component
public class BloodSampleCollection {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String date;
	private double time;
	private int period;
	private int aliquot;
	private String scanTime;
	private String comments;
	private String volunteerId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public String getVolunteerId() {
		return volunteerId;
	}
	public void setVolunteerId(String volunteerId) {
		this.volunteerId = volunteerId;
	}
	public int getAliquot() {
		return aliquot;
	}
	public void setAliquot(int aliquot) {
		this.aliquot = aliquot;
	}
}
