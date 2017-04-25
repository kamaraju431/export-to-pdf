package com.aizant.jasper;

import com.aizant.model.StudyVolunteer;

public class StudyVolunteerDataSource {
	StudyVolunteer studyVolunteer;
	
	public StudyVolunteer getStudyVolunteer() {
		return this.studyVolunteer;
	}
	
	public void setStudyVolunteer(StudyVolunteer studyVolunteer) {
		this.studyVolunteer = studyVolunteer;
	}
	
	public StudyVolunteerDataSource(StudyVolunteer studyVolunteer) {
		this.studyVolunteer = studyVolunteer;
	}
}
