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
import com.aizant.model.Study;
import com.aizant.model.StudyVolunteer;

@Repository("bloodSampleCollectionDAO")

public class BloodSampleCollectionDAOImpl  implements BloodSampleCollectionDAO {

	@Autowired
	private SessionFactory sessionFactory;
	public BloodSampleCollectionDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	@Transactional
	public String saveOrUpdate(BloodSampleCollection bloodSampleCollection) {
		sessionFactory.getCurrentSession().saveOrUpdate(bloodSampleCollection);
		return bloodSampleCollection.getId();
	}
	@Transactional
	public void delete(String id) {
		BloodSampleCollection BloodSampleCollectionToDelete =new BloodSampleCollection();
		BloodSampleCollectionToDelete.setId(id);
		sessionFactory.getCurrentSession().delete(BloodSampleCollectionToDelete);
		
	}
	@Transactional
	public BloodSampleCollection get(String id)
	{
		String hql ="from BloodSampleCollection where id="+"'"+id+"'";
		Query query =(Query) sessionFactory.getCurrentSession().createQuery(hql);
		List<BloodSampleCollection> listBloodSampleCollection =(List<BloodSampleCollection>) query.getResultList();
		if(listBloodSampleCollection != null && !listBloodSampleCollection.isEmpty())
		{
			return listBloodSampleCollection.get(0);
		}	
		return null;
	
	}
	
	
@Transactional
public List getAllBloodSampleCollection() {
	// TODO Auto-generated method stub
	Session session=sessionFactory.openSession();
	List list=session.createQuery("from BloodSampleCollection").list();
	session.close();
	return list;
	}

@Transactional
public String deleteBloodSampleCollection(String id) {
	// TODO Auto-generated method stub
	Session session=sessionFactory.openSession();
	org.hibernate.Transaction tx=session.beginTransaction();
	BloodSampleCollection bloodSampleCollection=(BloodSampleCollection)session.load(BloodSampleCollection.class, id);
	session.delete(bloodSampleCollection);
	tx.commit();
	
	session.close();
	return id;
}

public List<BloodSampleCollection> list() {
	Session session = sessionFactory.openSession();
	org.hibernate.Transaction tx = session.beginTransaction();
	List<BloodSampleCollection> list = session.createQuery("from BloodSampleCollection").list();
	tx.commit();
	session.close();
	return list;
}
/*@Override
public BloodSampleCollection getbyId(String volunteerId) {
	String hql ="from (select * from BloodSampleCollection) as StudyVolunteer where volunteerId='" + volunteerId + "'";
	Query query =(Query) sessionFactory.getCurrentSession().createQuery(hql);
	List<BloodSampleCollection> listBloodSampleCollection =(List<BloodSampleCollection>) query.getResultList();
	if(listBloodSampleCollection != null && !listBloodSampleCollection.isEmpty())
	{
		return listBloodSampleCollection.get(0);
	}	
	return null;

}*/


}