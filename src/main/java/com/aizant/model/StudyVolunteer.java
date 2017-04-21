package com.aizant.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.google.gson.annotations.Expose;

@Entity
@Table(name = "StudyVolunteer")
@Component
public class StudyVolunteer {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="studyVolunteer_id")
    @Expose
	private String id;
	@Expose
	private String registerNumber;
	@OneToMany(fetch = FetchType.LAZY)
	@Expose
	private List<BloodSampleCollection> bloodSampleCollection;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "study_id", nullable = false)
	@JsonBackReference
    @Expose(serialize = false)
	private Study study;
	
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

	public String getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}

	public Study getStudy() {
		return study;
	}

	public void setStudy(Study study) {
		this.study = study;
	}
}
