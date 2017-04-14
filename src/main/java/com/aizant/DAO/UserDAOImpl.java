package com.aizant.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aizant.model.User;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void saveOrUpdate(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	@Transactional
	public void delete(String id) {
		User userToDelete = new User();
		userToDelete.setId(id);
		sessionFactory.getCurrentSession().delete(userToDelete);

	}

	@Transactional
	public User get(String id) {
		String hql = "from User where id=" + "'" + id + "'";
		Query query = (Query) sessionFactory.getCurrentSession().createQuery(hql);
		List<User> listUser = (List<User>) query.getResultList();
		if (listUser != null || listUser.isEmpty()) {
			return listUser.get(0);
		}
		return null;

	}

	@Transactional
	public User getByUsername(String username) {
		String hql = "from User where username=" + "'" + username + "'";
		Query query = (Query) sessionFactory.getCurrentSession().createQuery(hql);
		List<User> listUser = (List<User>) query.getResultList();
		if (listUser != null || listUser.isEmpty()) {
			return listUser.get(0);
		}
		return null;

	}

	@Transactional
	public List<User> list() {

		Session session = sessionFactory.openSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		List<User> list = session.createQuery("from User").list();
		tx.commit();
		session.close();
		return list;
	}

	@Transactional
	public List getAllUsers() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		List list = session.createQuery("from User").list();
		tx.commit();
		session.close();

		return list;
	}

	@Transactional
	public String deleteUser(String id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		User user = (User) session.load(User.class, id);
		session.delete(user);
		tx.commit();

		session.close();
		return id;

	}
	public List<User> getUserByPage(int pageid,int total){
		Session session = sessionFactory.openSession();

		Query query = session.createQuery("FROM User");
		query.setFirstResult((pageid - 1) * total);
		query.setMaxResults(total);


		List<User> list =(List<User>) query.getResultList();

		session.close();
		return list;
	}

	public int getPageCount() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		org.hibernate.Transaction tx = session.beginTransaction();
		Long count = (Long) session.createQuery("SELECT COUNT(id) FROM User").getSingleResult();
		System.out.println("Count from db " + count);
		tx.commit();
		session.close();

		return (int) Math.ceil(count / 10.0);
	}
}
