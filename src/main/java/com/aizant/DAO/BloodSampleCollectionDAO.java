package com.aizant.DAO;

import java.util.List;


import com.aizant.model.BloodSampleCollection;
public interface BloodSampleCollectionDAO {
	public List<BloodSampleCollection> list();
	public BloodSampleCollection get(int id);

	public void saveOrUpdate(BloodSampleCollection bloodSampleCollection);
	public void delete(int id);
	public void delete(BloodSampleCollection BloodSampleCollectionToDelete);
	public List getAllBloodSampleCollection();
	public int deleteBloodSampleCollection(int id);
/*	  public BloodSampleCollection getbyId(String volunteerId) ;*/
	


}
