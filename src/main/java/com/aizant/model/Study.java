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

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.gson.annotations.Expose;

@Entity
@Table(name ="Study")
@Component
public class Study {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="study_id")
	@Expose
	private String id;
	
	@Expose
	private String name;
	@Expose
	private int aliquot;
	@Expose
	private int sampleCollectionSize_in_ml;
	@Expose
	private int periods;
	@Expose
	private String clientStudyId;
	@Expose
	private String date;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="study")
	@JsonManagedReference
	@Expose
	private List<StudyVolunteer> studyVolunteers;
	
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@Expose
	private List<SampleTime> sampleTime;

	/* --------------- Getter setters --------------------- */
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
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
	
	
	public int getSampleCollectionSize_in_ml() {
		return sampleCollectionSize_in_ml;
	}
	public void setSampleCollectionSize_in_ml(int sampleCollectionSize_in_ml) {
		this.sampleCollectionSize_in_ml = sampleCollectionSize_in_ml;
	}
	public int getPeriods() {
		return periods;
	}
	public void setPeriods(int periods) {
		this.periods = periods;
	}
	public String getClientStudyId() {
		return clientStudyId;
	}
	public void setClientStudyId(String clientStudyId) {
		this.clientStudyId = clientStudyId;
	}
	public List<StudyVolunteer> getStudyVolunteers() {
		return studyVolunteers;
	}
	public void setStudyVolunteers(List<StudyVolunteer> studyVolunteers) {
		this.studyVolunteers = studyVolunteers;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public List<SampleTime> getSampleTime() {
		return sampleTime;
	}

	public void setSampleTime(List<SampleTime> sampleTime) {
		this.sampleTime = sampleTime;
	}

}
