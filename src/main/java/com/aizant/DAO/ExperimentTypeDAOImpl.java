package com.aizant.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aizant.model.ExperimentType;
import com.aizant.model.Login;
import com.aizant.model.ExperimentType;

@Repository("experimentTypeDAO")
public class ExperimentTypeDAOImpl implements ExperimentTypeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public ExperimentTypeDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void saveOrUpdate(ExperimentType experiment_Type) {
		sessionFactory.getCurrentSession().saveOrUpdate(experiment_Type);
	}

	@Transactional
	public void delete(int id) {
		ExperimentType experiment_TypeToDelete = new ExperimentType();
		experiment_TypeToDelete.setId(id);
		sessionFactory.getCurrentSession().delete(experiment_TypeToDelete);

	}

	@Transactional
	public ExperimentType get(int id) {
		String hql = "from ExperimentType where id=" + "'" + id + "'";
		Query query = (Query) sessionFactory.getCurrentSession().createQuery(hql);
		List<ExperimentType> listExperimentType = (List<ExperimentType>) query.getResultList();
		if (listExperimentType != null || listExperimentType.isEmpty()) {
			return listExperimentType.get(0);
		}
		return null;

	}

	@Transactional
	public List<ExperimentType> list() {

		Session session = sessionFactory.openSession();
		List<ExperimentType> list = session.createQuery("from ExperimentType").list();
		session.close();
		return list;
	}

	@Transactional
	public List getAllExperimentTypes() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		List list = session.createQuery("from ExperimentType").list();
		session.close();
		return list;
	}

	@Transactional
	public int deleteExperimentType(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		ExperimentType experiment_Type = (ExperimentType) session.load(ExperimentType.class, id);
		session.delete(experiment_Type);
		tx.commit();

		session.close();
		return id;
	}

	public List<ExperimentType> getExperimentTypeByPage(int pageid, int total) {
		Session session = sessionFactory.openSession();

		Query query = session.createQuery("FROM ExperimentType");
		query.setFirstResult((pageid - 1) * total + 1);
		query.setMaxResults(total);

		List<ExperimentType> list = (List<ExperimentType>) query.getResultList();

		session.close();
		return list;
	}

	@Override
	public long getPageCount() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		long count = (long) session.createQuery("SELECT COUNT(id) FROM ExperimentType").getSingleResult();
		System.out.println("Count from db " + count);
		session.close();

		return (int) Math.ceil(count / 10.0);
	}

}
