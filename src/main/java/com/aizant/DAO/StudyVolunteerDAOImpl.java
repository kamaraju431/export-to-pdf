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
	public void saveOrUpdate(StudyVolunteer study_Volunteer) {
		sessionFactory.getCurrentSession().saveOrUpdate(study_Volunteer);
	}

	@Transactional
	public void delete(String id) {
		StudyVolunteer volunteerToDelete = new StudyVolunteer();
		volunteerToDelete .setId(id);
		sessionFactory.getCurrentSession().delete(volunteerToDelete );
	}
	
	@Transactional
	public void delete(StudyVolunteer volunteerToDelete) {
		sessionFactory.getCurrentSession().delete(volunteerToDelete );

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
		Session session = sessionFactory.getCurrentSession();
		for (StudyVolunteer volunteer : volunteers) {
			session.save(volunteer);
		}
	}

	@Transactional
	public List<StudyVolunteer> list() {

		Session session = sessionFactory.getCurrentSession();
		List<StudyVolunteer> list = session.createQuery("from StudyVolunteer").list();
		return list;
	}

	@Transactional
	public List getAllStudyVolunteer() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List list = session.createQuery("from StudyVolunteer").list();
		return list;
	}

	/*@Transactional
	public void deleteFromStudy(String id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		StudyVolunteer study_Volunteer = (StudyVolunteer) session.load(StudyVolunteer.class, id);
		session.delete(study_Volunteer);

	}*/

	@Transactional
	public int getPageCount() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Long count = (Long) session.createQuery("SELECT COUNT(id) FROM StudyVolunteer").getSingleResult();
		System.out.println("Count from db " + count);
		return (int) Math.ceil(count / 10.0);
	}

	@Transactional
	public List<StudyVolunteer> getExperimentByPage(int pageid, int total) {
		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery("FROM StudyVolunteer");
		query.setFirstResult((pageid - 1) * total);
		query.setMaxResults(total);

		List<StudyVolunteer> list = (List<StudyVolunteer>) query.getResultList();
		return list;
	}

	@Transactional
	public void addVolunteersToStudy(List<StudyVolunteer> studyVolunteers) {
		Session session = sessionFactory.getCurrentSession();
		sessionFactory.getCurrentSession().save(studyVolunteers);
	}

	public List getAllStudyCollections() {
		Session session = sessionFactory.getCurrentSession();
		List list = session.createQuery("from BloodSampleCollection").list();
		return list;

	}

}
