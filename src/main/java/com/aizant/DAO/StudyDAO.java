package com.aizant.DAO;

import java.util.List;

import com.aizant.model.Study;
import com.aizant.model.User;



public interface StudyDAO {
	public List<Study> list();
	public Study get(String id);
	public String saveOrUpdate(Study study);
	public void delete(String id);
	public List<Study> getStudyByPage(int pageid,int total);
	public int getPageCount();
	public List getAllStudy();
	public String deleteStudy(String id);
    public Study getbyName(String name) ;
 


}
