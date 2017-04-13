package com.aizant.DAO;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aizant.model.Study;

@Repository("studyDAO")

public class StudyDAOImpl implements StudyDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public StudyDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public String saveOrUpdate(Study study) {
		System.out.println("STUDY SAVING" + study.getSampleTime());
		sessionFactory.getCurrentSession().saveOrUpdate(study);
		return study.getId();
	}

	@Transactional
	public void delete(String id) {
		Study experimentTypeToDelete = new Study();
		experimentTypeToDelete.setId(id);
		sessionFactory.getCurrentSession().delete(experimentTypeToDelete);

	}

	@Transactional
	public Study get(String id) {
		String hql = "from Study where id=" + "'" + id + "'";
		System.out.println("GOT THIS FAR 3" + hql);
		Query query = (Query) sessionFactory.getCurrentSession().createQuery(hql);
		System.out.println("GOT THIS FAR 4" + query);		
		List<Study> listStudy = (List<Study>) query.getResultList();
		if (listStudy != null && !listStudy.isEmpty()) {
			return listStudy.get(0);
		}
		return null;
	}
	
	//
	// @Transactional
	// public Study getEager(int id)
	// {
	// String hql ="from Study where id="+"'"+id+"'";
	// Query query =(Query) sessionFactory.getCurrentSession().createQuery(hql);
	// List<Study> listStudy =(List<Study>) query.getResultList();
	// if(listStudy != null && !listStudy.isEmpty())
	// {
	// Study study = listStudy.get(0);
	// System.out.println("lengt........................");
	// study.setStudyVolunteers(study.getStudyVolunteers());
	// return listStudy.get(0);
	// }
	//
	// return null;
	//
	// }

	@Transactional
	public Study getbyName(String name) {
		String hql = "from Study  where name='" + name + "'";
		// String hql ="from Study where id=71";
		System.out.println("This is the query: " + hql);
		Query query = (Query) sessionFactory.getCurrentSession().createQuery(hql);
		List<Study> listStudy = (List<Study>) query.getResultList();
		if (listStudy != null || listStudy.isEmpty()) {
			return listStudy.get(0);
		}
		return null;

	}

	@Transactional
	public List getAllStudy() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List list = session.createQuery("from Study").list();
		
		return list;
	}

	@Transactional
	public String deleteStudy(String id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Study study = (Study) session.load(Study.class, id);
		session.delete(study);
		return id;
	}

	@Transactional
	public List<Study> getStudyByPage(int pageid, int total) {
		
		Query query = sessionFactory.getCurrentSession().createQuery("FROM Study");
		query.setFirstResult((pageid - 1) * total);
		query.setMaxResults(total);
		List<Study> list = (List<Study>) query.getResultList();
	
		return list;
	}

	@Override
	@Transactional
	public long getPageCount() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		long count = (long) session.createQuery("SELECT COUNT(id) FROM Study").getSingleResult();
		System.out.println("Count from db " + count);
		

		return (int) Math.ceil(count / 10.0);
	}

	@Override
	@Transactional
	public List<Study> list() {
		Session session = sessionFactory.getCurrentSession();
		List<Study> list = session.createQuery("from Study").list();
	
		return list;
	}

}
