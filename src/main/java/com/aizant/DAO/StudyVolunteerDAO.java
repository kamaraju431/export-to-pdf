package com.aizant.DAO;

import java.util.List;

import com.aizant.model.StudyVolunteer;

public interface StudyVolunteerDAO {
	
	public List<StudyVolunteer> list();
	public void save(List<StudyVolunteer> volunteers);

	public StudyVolunteer get(String id);

	public void saveOrUpdate(StudyVolunteer study_Volunteer);

	public void delete(String id);
	public void delete(StudyVolunteer volunteerToDelete);


	public List getAllStudyVolunteer();
	public List getAllStudyCollections();

	public List<StudyVolunteer> getExperimentByPage(int pageid, int total);

	public int getPageCount();
	
	public void addVolunteersToStudy(List<StudyVolunteer> studyVolunteers);

	
}
