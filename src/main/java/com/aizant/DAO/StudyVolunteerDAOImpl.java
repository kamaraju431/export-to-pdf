package com.aizant.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aizant.model.StudyVolunteer;

@Repository("studyVolunteerDAO")
public class StudyVolunteerDAOImpl implements StudyVolunteerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public StudyVolunteerDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void save(StudyVolunteer study_Volunteer) {
		sessionFactory.getCurrentSession().save(study_Volunteer);
	}

	@Transactional
	public void Update(StudyVolunteer study_Volunteer) {
		sessionFactory.getCurrentSession().update(study_Volunteer);
	}

	@Transactional
	public void delete(String id) {
		StudyVolunteer experimentToDelete = new StudyVolunteer();
		experimentToDelete.setId(id);
		sessionFactory.getCurrentSession().delete(experimentToDelete);

	}

	@Transactional
	public StudyVolunteer get(String id) {
		String hql = "from StudyVolunteer where id=" + "'" + id + "'";
		Query query = (Query) sessionFactory.getCurrentSession().createQuery(hql);
		List<StudyVolunteer> listExperiment = (List<StudyVolunteer>) query.getResultList();
		if (listExperiment != null && !listExperiment.isEmpty()) {
			return listExperiment.get(0);
		}
		return null;

	}

	@Transactional
	public void save(List<StudyVolunteer> volunteers) {
		Session session = sessionFactory.openSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		for (StudyVolunteer volunteer : volunteers) {
			session.save(volunteer);
		}
		tx.commit();
		session.close();
	}

	@Transactional
	public List<StudyVolunteer> list() {

		Session session = sessionFactory.openSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		List<StudyVolunteer> list = session.createQuery("from StudyVolunteer").list();
		tx.commit();
		session.close();
		return list;
	}

	@Transactional
	public List getAllStudyVolunteer() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		List list = session.createQuery("from StudyVolunteer").list();
		tx.commit();
		session.close();
		return list;
	}

	@Transactional
	public String deleteStudyVolunteer(String id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		org.hibernate.Transaction tx = session.beginTransaction();

		StudyVolunteer study_Volunteer = (StudyVolunteer) session.load(StudyVolunteer.class, id);
		session.delete(study_Volunteer);
		tx.commit();

		session.close();
		return id;

	}

	@Override
	public long getPageCount() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		long count = (long) session.createQuery("SELECT COUNT(id) FROM StudyVolunteer").getSingleResult();
		System.out.println("Count from db " + count);
		tx.commit();
		session.close();

		return (int) Math.ceil(count / 10.0);
	}

	@Transactional
	public List<StudyVolunteer> getExperimentByPage(int pageid, int total) {
		Session session = sessionFactory.openSession();

		Query query = session.createQuery("FROM StudyVolunteer");
		query.setFirstResult((pageid - 1) * total + 1);
		query.setMaxResults(total);

		List<StudyVolunteer> list = (List<StudyVolunteer>) query.getResultList();
	

		session.close();
		return list;
	}

	@Override
	public void addVolunteersToStudy(List<StudyVolunteer> studyVolunteers) {
		Session session = sessionFactory.openSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		sessionFactory.getCurrentSession().save(studyVolunteers);
		tx.commit();
		session.close();

	}

}
