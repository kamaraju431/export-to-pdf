package com.aizant.model;


public class Sample {
	private double time;
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
		this.id= id;
	}

	public Sample(String id) {
		super();
		this.id = id;
	}

	public Sample(double time) {
		super();
		this.time = time;
	}

	

}
