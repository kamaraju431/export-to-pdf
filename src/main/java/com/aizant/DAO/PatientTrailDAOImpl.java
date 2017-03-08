package com.aizant.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aizant.model.PatientTrail;

@Repository("patienttrailDAO")
public class PatientTrailDAOImpl implements PatientTrailDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public PatientTrailDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void save(PatientTrail patient_Trail) {
		sessionFactory.getCurrentSession().save(patient_Trail);
	}

	@Transactional
	public void Update(PatientTrail patient_Trail) {
		sessionFactory.getCurrentSession().update(patient_Trail);
	}

	@Transactional
	public void delete(int id) {
		PatientTrail experimentToDelete = new PatientTrail();
		experimentToDelete.setId(id);
		sessionFactory.getCurrentSession().delete(experimentToDelete);

	}

	@Transactional
	public PatientTrail get(int id) {
		String hql = "from PatientTrail where id=" + "'" + id + "'";
		Query query = (Query) sessionFactory.getCurrentSession().createQuery(hql);
		List<PatientTrail> listExperiment = (List<PatientTrail>) query.getResultList();
		if (listExperiment != null || listExperiment.isEmpty()) {
			return listExperiment.get(0);
		}
		return null;

	}

	@Transactional
	public List<PatientTrail> list() {

		Session session = sessionFactory.openSession();
		List<PatientTrail> list = session.createQuery("from PatientTrail").list();
		session.close();
		return list;
	}

	@Transactional
	public List getAllExperiments() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		List list = session.createQuery("from PatientTrail").list();
		session.close();
		return list;
	}

	@Transactional
	public int deleteexperiment(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		PatientTrail patient_Trail = (PatientTrail) session.load(PatientTrail.class, id);
		session.delete(patient_Trail);
		tx.commit();

		session.close();
		return id;

	}

	public List<PatientTrail> getExperimentByPage(int pageid, int total) {
		Session session = sessionFactory.openSession();

		Query query = session.createQuery("FROM PatientTrail");
		query.setFirstResult((pageid - 1) * total + 1);
		query.setMaxResults(total);

		List<PatientTrail> list = (List<PatientTrail>) query.getResultList();

		session.close();
		return list;
	}

	@Override
	public long getPageCount() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		long count = (long) session.createQuery("SELECT COUNT(id) FROM PatientTrail").getSingleResult();
		System.out.println("Count from db " + count);
		session.close();

		return (int) Math.ceil(count / 10.0);
	}

}
