package com.aizant.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aizant.DAO.BloodSampleCollectionDAO;
import com.aizant.DAO.StudyVolunteerDAO;
import com.aizant.model.BloodSampleCollection;
import com.aizant.model.StudyVolunteer;

@Transactional
@Service
public class StudyVolunteerService implements IStudyVolunteerService{

	@Autowired
    private StudyVolunteerDAO studyVolunteerDao;
  
    @Autowired
    private BloodSampleCollectionDAO bloodSampleCollectionDao;
    
    @Transactional
    public StudyVolunteer get(String id, boolean showBloodSamples)  {
    	StudyVolunteer volunteer = studyVolunteerDao.get(id);
    	volunteer.getStudy();
    	if (showBloodSamples) {
    		volunteer.getBloodSampleCollection();
    		for(BloodSampleCollection sample: volunteer.getBloodSampleCollection()) {
        		System.out.println("BLOOD SAMPLE COLLECTIONS" + sample.getId());    			
    		}
    	}
    	return volunteer;
    }
    
    @Transactional
    public void delete(StudyVolunteer volunteer) {	
    	for (BloodSampleCollection sampleCollection : volunteer.getBloodSampleCollection()) {
		System.out.println("blood collection" + sampleCollection);
    		bloodSampleCollectionDao.delete(sampleCollection);
    		System.out.println("blood collection delete" + sampleCollection.getId());
    	}
		studyVolunteerDao.delete(volunteer);
    }
    
    @Transactional
    public void deleteFromStudy(String volunteerId) {	
    	StudyVolunteer volunteer = studyVolunteerDao.get(volunteerId);
    	System.out.println("Study volunteer to delete" + volunteer.getBloodSampleCollection());
    	for (BloodSampleCollection sampleCollection : volunteer.getBloodSampleCollection()) {
		System.out.println("blood collection" + sampleCollection);
    		bloodSampleCollectionDao.delete(sampleCollection);
    		System.out.println("blood collection delete" + sampleCollection.getId());
    	}
		studyVolunteerDao.delete(volunteer);
    }

}    
