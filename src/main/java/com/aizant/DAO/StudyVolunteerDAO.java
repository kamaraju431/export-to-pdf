package com.aizant.DAO;

import java.util.List;

import com.aizant.model.StudyVolunteer;

public interface StudyVolunteerDAO {
	
	public List<StudyVolunteer> list();
	public void save(List<StudyVolunteer> volunteers);

	public StudyVolunteer get(String id);

	public void save(StudyVolunteer study_Volunteer);

	public void Update(StudyVolunteer study_Volunteer);

	public void delete(String id);

	public List getAllStudyVolunteer();
	public List getAllStudyCollections();
	

	

	public String deleteStudyVolunteer(String id);

	public List<StudyVolunteer> getExperimentByPage(int pageid, int total);

	public int getPageCount();
	
	public void addVolunteersToStudy(List<StudyVolunteer> studyVolunteers);

	
}
