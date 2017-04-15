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
public class StudyVolunteerService implements IStudyVolunteerService{
    @Autowired
    private StudyVolunteerDAO studyVolunteerDao;
     
    @Transactional
    public StudyVolunteer get(String id)  {
    	StudyVolunteer volunteer = studyVolunteerDao.get(id);
    	volunteer.getStudy();
    	return volunteer;
    }
}    
