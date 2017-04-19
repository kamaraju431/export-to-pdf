package com.aizant.DAO;

import java.util.List;

import com.aizant.model.BloodSampleCollection;
import com.aizant.model.Study;
import com.aizant.model.StudyVolunteer;public interface BloodSampleCollectionDAO {
	public List<BloodSampleCollection> list();
	public BloodSampleCollection get(String id);

	public String saveOrUpdate(BloodSampleCollection bloodSampleCollection);
	public void delete(String id);
	public void delete(BloodSampleCollection BloodSampleCollectionToDelete);
	public List getAllBloodSampleCollection();
	public String deleteBloodSampleCollection(String id);
/*	  public BloodSampleCollection getbyId(String volunteerId) ;*/
	


}
