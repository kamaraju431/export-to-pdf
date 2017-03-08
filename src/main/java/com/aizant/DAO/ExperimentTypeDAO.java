package com.aizant.DAO;

import java.util.List;

import com.aizant.model.ExperimentType;


public interface ExperimentTypeDAO {
	public List<ExperimentType> list();

	public ExperimentType get(int id);

	public void saveOrUpdate(ExperimentType experiment_Type);

	public void delete(int id);

	public List<ExperimentType> getExperimentTypeByPage(int pageid, int total);

	public long getPageCount();

	public List getAllExperimentTypes();

	public int deleteExperimentType(int id);

}
