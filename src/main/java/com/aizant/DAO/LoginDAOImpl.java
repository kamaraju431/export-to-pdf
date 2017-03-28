package com.aizant.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aizant.model.Login;

@Repository("loginDAO")
public class LoginDAOImpl implements LoginDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public LoginDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void save(Login login) {
		sessionFactory.getCurrentSession().save(login);
	}

	@Transactional
	public void Update(Login login) {
		sessionFactory.getCurrentSession().update(login);
	}

	@Transactional
	public void delete(int id) {
		Login userToDelete = new Login();
		userToDelete.setId(id);
		sessionFactory.getCurrentSession().delete(userToDelete);

	}

	@Transactional
	public Login get(int id) {
		String hql = "from Login where id=" + "'" + id + "'";
		Query query = (Query) sessionFactory.getCurrentSession().createQuery(hql);
		List<Login> listUser = (List<Login>) query.getResultList();
		if (listUser != null || !listUser.isEmpty()) {
			return listUser.get(0);
		}
		return null;
	}

	@Transactional
	public List<Login> list() {
		@SuppressWarnings("unchecked")
		List<Login> listUser = (List<Login>) sessionFactory.getCurrentSession().createCriteria(Login.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return listUser;
	}
	 

}
