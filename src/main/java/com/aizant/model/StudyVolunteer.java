package com.aizant.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "StudyVolunteer")
@Component
public class StudyVolunteer {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="studyVolunteer_id")
	private String id;
	

	private String volunteerId;
	private String volunteerName;
	@OneToMany(fetch = FetchType.LAZY)
	private List<BloodSampleCollection> bloodSampleCollection;

	/* --------------- Getter setters --------------------- */
	
	public List<BloodSampleCollection> getBloodSampleCollection() {
		return bloodSampleCollection;
	}

	public void setBloodSampleCollection(List<BloodSampleCollection> bloodSampleCollection) {
		this.bloodSampleCollection = bloodSampleCollection;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getVolunteerId() {
		return volunteerId;
	}

	public void setVolunteerId(String volunteerId) {
		this.volunteerId = volunteerId;
	}
	public String getVolunteerName() {
		return volunteerName;
	}

	public void setVolunteerName(String volunteerName) {
		this.volunteerName = volunteerName;
	}

}
