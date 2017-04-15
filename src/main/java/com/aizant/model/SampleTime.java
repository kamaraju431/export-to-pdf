package com.aizant.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "SampleTime")
@Component
public class SampleTime {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private double timePoint;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getTimePoint() {
		return timePoint;
	}
	public void setTimePoint(double timePoint) {
		this.timePoint = timePoint;
	}
	public SampleTime(String id) {
		super();
		this.id = id;
	}

	public SampleTime() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SampleTime(double timePoint) {
		super();
		this.timePoint = timePoint;
	}
	public SampleTime(double timePoint, String id) {
		super();
		this.timePoint = timePoint;
		this.id= id;
	}
	
}
