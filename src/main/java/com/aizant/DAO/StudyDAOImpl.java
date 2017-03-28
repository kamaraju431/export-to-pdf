package com.aizant.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aizant.model.Study;
import com.aizant.model.Login;
import com.aizant.model.StudyVolunteer;
import com.aizant.model.Study;


@Repository("studyDAO")
public class StudyDAOImpl  implements StudyDAO {

	@Autowired
	private SessionFactory sessionFactory;
	public StudyDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	@Transactional
	public int saveOrUpdate(Study study) {
		sessionFactory.getCurrentSession().saveOrUpdate(study);
		return study.getId();
	}
	@Transactional
	public void delete(int id) {
		Study experimentTypeToDelete =new Study();
		experimentTypeToDelete.setId(id);
		sessionFactory.getCurrentSession().delete(experimentTypeToDelete);
		
	}
	@Transactional
	public Study get(int id)
	{
		String hql ="from Study where id="+"'"+id+"'";
		Query query =(Query) sessionFactory.getCurrentSession().createQuery(hql);
		List<Study> listStudy =(List<Study>) query.getResultList();
		if(listStudy != null || listStudy.isEmpty())
		{
			return listStudy.get(0);
		}	
		return null;
	
	}
	
	
//@Transactional
	//public Study getbyName(String name) {
   // String hql ="from Study  where name='"+name+"'";
	//String hql ="from Study where id=71";
	//System.out.println("This is the query: " + hql);
	//Query query =(Query) sessionFactory.getCurrentSession().createQuery(hql);
	//List<Study> listStudy =(List<Study>) query.getResultList();
	//if(listStudy != null && !listStudy.isEmpty())
	//{
	//	return listStudy.get(0);
	//}	
	//return null;

//}
		
@Transactional
public List getAllStudy() {
	// TODO Auto-generated method stub
	Session session=sessionFactory.openSession();
	List list=session.createQuery("from Study").list();
	session.close();
	return list;
	}

@Transactional
public int deleteStudy(int id) {
	// TODO Auto-generated method stub
	Session session=sessionFactory.openSession();
	org.hibernate.Transaction tx=session.beginTransaction();
	Study study=(Study)session.load(Study.class, id);
	session.delete(study);
	tx.commit();
	
	session.close();
	return id;
}

public List<Study> getStudyByPage(int pageid,int total){  
	Session session = sessionFactory.openSession();

	Query query = session.createQuery("FROM Study");
	query.setFirstResult((pageid - 1) * total + 1);
	query.setMaxResults(total);
	
	
	List<Study> list =(List<Study>) query.getResultList();

	session.close();
	return list;
        }
@Override
public long getPageCount() {
	// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		long count= (long) session.createQuery("SELECT COUNT(id) FROM Study").getSingleResult();
		System.out.println("Count from db " + count);
		session.close();
		
	return (int)Math.ceil(count/10.0);
}
@Override
public List<Study> list() {
	Session session = sessionFactory.openSession();
	org.hibernate.Transaction tx = session.beginTransaction();
	List<Study> list = session.createQuery("from Study").list();
	tx.commit();
	session.close();
	return list;
}



}
