package com.aizant.DAO;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aizant.model.BloodSampleCollection;
import com.aizant.model.SampleTime;
import com.aizant.model.Study;
import com.aizant.model.StudyVolunteer;

@Repository("sampleTimeDAO")

public class SampleTimeDAOImpl implements SampleTimeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public SampleTimeDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void saveOrUpdate(SampleTime sampleTime) {
		sessionFactory.getCurrentSession().saveOrUpdate(sampleTime);

	}
	@Transactional
	public void delete(String id) {
		SampleTime sample =new SampleTime();
		sample.setId(id);
		sessionFactory.getCurrentSession().delete(sample);
		

		
	}
	/*@Transactional
	public String deleteSampleTime(String id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
	SampleTime sampleTime = (SampleTime) session.load(SampleTime.class, id);
		session.delete(sampleTime);
		return id;

	}*/


	@Transactional
	public SampleTime get(String id) {
		String hql = "from SampleTime where id=" + "'" + id + "'";
		Query query = (Query) sessionFactory.getCurrentSession().createQuery(hql);
		List<SampleTime> listSampleTime = (List<SampleTime>) query.getResultList();
		if (listSampleTime != null && !listSampleTime.isEmpty()) {
			return listSampleTime.get(0);
		}
		return null;

	}
	/*
	 * @Transactional public void save(List<SampleTime> sampleTime) { Session
	 * session = sessionFactory.openSession(); org.hibernate.Transaction tx =
	 * session.beginTransaction(); for (SampleTime sampleTimes : sampleTime) {
	 * session.save(sampleTimes); } tx.commit(); session.close(); }
	 */

	@Transactional
	public List getAllSampleTime() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List list = session.createQuery("from SampleTime").list();
		return list;
	}

	@Transactional
	public List<SampleTime> list() {
		Session session = sessionFactory.getCurrentSession();
		List<SampleTime> list = session.createQuery("from SampleTime").list();
		return list;
	}
	
	
	/*
	 * @Override public void addSampleTimesToStudy(List<SampleTime> sampleTime)
	 * { Session session = sessionFactory.openSession();
	 * org.hibernate.Transaction tx = session.beginTransaction();
	 * sessionFactory.getCurrentSession().save(sampleTime); tx.commit();
	 * session.close();
	 * 
	 * }
	 */

}
