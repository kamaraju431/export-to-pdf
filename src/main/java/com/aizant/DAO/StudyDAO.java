package com.aizant.DAO;

import java.util.List;

import com.aizant.model.Study;
import com.aizant.model.User;



public interface StudyDAO {
	public List<Study> list();
	public Study get(int id);
	public int saveOrUpdate(Study study);
	public void delete(int id);
	public List<Study> getStudyByPage(int pageid,int total);
	public long getPageCount();
	public List getAllStudy();
	public int deleteStudy(int id);
    //public Study getbyName(String name) ;
 


}
