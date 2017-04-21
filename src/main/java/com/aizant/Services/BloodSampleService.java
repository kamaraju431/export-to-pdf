package com.aizant.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aizant.DAO.BloodSampleCollectionDAO;
import com.aizant.model.BloodSampleCollection;

@Transactional
@Service
public class BloodSampleService implements IBloodSampleService {
	 @Autowired
	    private BloodSampleCollectionDAO bloodSampleCollectionDao;
	 @Transactional
	    public BloodSampleCollection get(String id, boolean showBloodSamples)  {
	    	BloodSampleCollection volunteer = bloodSampleCollectionDao.get(id);
	    
	    		volunteer.getId();
	    		
	    	return volunteer;
	    }
	    @Transactional
	public void deleteFromStudyVolunteer(String BloodSampleId) {
	    	BloodSampleCollection bloodSample = bloodSampleCollectionDao.get(BloodSampleId);
	    	System.out.println("Study volunteer to delete" + bloodSample.getId());
		bloodSampleCollectionDao.delete(bloodSample);
	}
}
