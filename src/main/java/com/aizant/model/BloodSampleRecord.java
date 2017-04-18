package com.aizant.model;

public class BloodSampleRecord {
	private String date;
	private double time;
	private int period;
	private String scanTime;
	private String comments;
	private String volunteerId;
	 
	
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
	public BloodSampleRecord(String date) {
		super();
		this.date = date;
	}
	public BloodSampleRecord(double time) {
		super();
		this.time = time;
	}
	public BloodSampleRecord(int period) {
		super();
		this.period = period;
	}
	public BloodSampleRecord(String date, double time, int period, String scanTime, String comments,
			String volunteerId) {
		super();
		this.date = date;
		this.time = time;
		this.period = period;
		this.scanTime = scanTime;
		this.comments = comments;
		this.volunteerId = volunteerId;
	}	

}
