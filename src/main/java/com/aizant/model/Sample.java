package com.aizant.model;


public class Sample {
	private double time;
	private String date;
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public Sample(double time, String date) {
		super();
		this.time = time;
		this.date = date;
	}

	public Sample(double time) {
		super();
		this.time = time;
	}

	

}
