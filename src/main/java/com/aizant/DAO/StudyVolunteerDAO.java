package com.aizant.DAO;

import java.util.List;

import com.aizant.model.StudyVolunteer;

public interface StudyVolunteerDAO {
	
	public List<StudyVolunteer> list();
	public void save(List<StudyVolunteer> volunteers);

	public StudyVolunteer get(int id);

	public void save(StudyVolunteer study_Volunteer);

	public void Update(StudyVolunteer study_Volunteer);

	public void delete(int id);

	public List getAllStudyVolunteer();
	

	

	public int deleteStudyVolunteer(int id);

	public List<StudyVolunteer> getExperimentByPage(int pageid, int total);

	public long getPageCount();
	
	public void addVolunteersToStudy(List<StudyVolunteer> studyVolunteers);

	
}
