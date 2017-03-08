package com.aizant.DAO;

import java.util.List;

import com.aizant.model.PatientTrail;

public interface PatientTrailDAO {
	public List<PatientTrail> list();

	public PatientTrail get(int id);

	public void save(PatientTrail patient_Trail);

	public void Update(PatientTrail patient_Trail);

	public void delete(int id);

	public List getAllExperiments();

	public int deleteexperiment(int id);

	public List<PatientTrail> getExperimentByPage(int pageid, int total);

	public long getPageCount();
}
