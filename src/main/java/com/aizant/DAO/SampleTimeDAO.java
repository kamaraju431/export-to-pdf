package com.aizant.DAO;

import java.util.List;

import com.aizant.model.SampleTime;
import com.aizant.model.StudyVolunteer;



public interface SampleTimeDAO {
	public List<SampleTime> list();
/*	public void save(List<SampleTime> sampleTime);*/
	public SampleTime get(String id);
	public void saveOrUpdate(SampleTime sampleTime);
	public List getAllSampleTime();
	public void delete(String id);
/*	public String deleteSampleTime(String id);*/
  /*  public void addSampleTimesToStudy(List<SampleTime> sampleTime);*/
	
	
  
 


}
