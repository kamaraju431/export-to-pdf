package com.aizant.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aizant.DAO.SampleTimeDAO;
import com.aizant.DAO.StudyDAO;
import com.aizant.DAO.StudyVolunteerDAO;
import com.aizant.model.SampleTime;
import com.aizant.model.Study;
import com.aizant.model.StudyVolunteer;

@Transactional
@Service
public class StudyService implements IStudyService{
    @Autowired
    private StudyDAO studyDao;
    
    @Autowired
    private SampleTimeDAO sampleTimeDao;
    
    @Autowired
    private StudyVolunteerDAO studyVolunteerDao;
    
    @Autowired
    private IStudyVolunteerService studyVolunteerService;
     
    @Transactional
    public void saveOrUpdate(Study study)  {
    	studyDao.saveOrUpdate(study);
    	for (StudyVolunteer volunteer: study.getStudyVolunteers()) {
    		volunteer.setStudy(study);
    		studyVolunteerDao.saveOrUpdate(volunteer);
    	}
    }
    
    @Transactional
    public Study get(String id) {
    	Study study = studyDao.get(id);
    	study.getStudyVolunteers();
    	study.getSampleTime();
    	return study;
    }
    
    @Transactional
    public void delete(String id) {
    	Study study = studyDao.get(id);
    	System.out.println("study delete id"+study.getId());
    	for(SampleTime sampleTime: study.getSampleTime()) {
    		System.out.println(" SampleTime delete id"+sampleTime.getId());
    	}
    	
    	for(StudyVolunteer studyVolunteer: study.getStudyVolunteers()) {
    		studyVolunteerService.delete(studyVolunteer);
    		System.out.println("volunteer delete" + studyVolunteer.getId());
    	}
    	System.out.println("at last");
    	studyDao.delete(study);
    	System.out.println("at last delete" + study.getId());
    }
}    
