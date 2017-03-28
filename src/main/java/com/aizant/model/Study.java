package com.aizant.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Entity
@Table(name ="Study")
@Component
public class Study {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="study_id")
	private String id;
	
	private String name;
	private int aliquot;
	private double[] sample;
	private int sampleCollectionSize_in_ml;
	private int periods;
	private String clientStudyId;
	private String date;
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private List<StudyVolunteer> studyVolunteers;	

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
	
	public double[] getSample() {
		return sample;
	}
	public void setSample(double[] sample) {
		this.sample = sample;
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

	
	

	

}
