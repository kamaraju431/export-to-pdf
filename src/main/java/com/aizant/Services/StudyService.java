package com.aizant.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aizant.DAO.StudyDAO;
import com.aizant.DAO.StudyVolunteerDAO;
import com.aizant.model.Study;
import com.aizant.model.StudyVolunteer;

@Transactional
@Service
public class StudyService implements IStudyService{
    @Autowired
    private StudyDAO studyDao;
    
    @Autowired
    private StudyVolunteerDAO studyVolunteerDao;
     
    @Transactional
    public void saveOrUpdate(Study study)  {
    	studyDao.saveOrUpdate(study);
    	for (StudyVolunteer volunteer: study.getStudyVolunteers()) {
    		volunteer.setStudy(study);
    		studyVolunteerDao.saveOrUpdate(volunteer);
    	}
    }
}    
