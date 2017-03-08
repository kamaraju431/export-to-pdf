package com.aizant.model;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.hibernate5.HibernateTransactionManager;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.aizant.DAO.PatientTrailDAO;
import com.aizant.DAO.PatientTrailDAOImpl;
import com.aizant.DAO.ExperimentTypeDAO;
import com.aizant.DAO.ExperimentTypeDAOImpl;
import com.aizant.DAO.LoginDAO;
import com.aizant.DAO.LoginDAOImpl;

import com.aizant.DAO.UserDAO;
import com.aizant.DAO.UserDAOImpl;

@Configuration
@ComponentScan("com.aizant")
@EnableTransactionManagement
public class ApplicationContextConfig {
	@Bean(name = "dataSource")
	public DataSource getH2DataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:tcp://localhost/~/aizant");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		return dataSource;

	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

		return properties;

	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionFactoryBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionFactoryBuilder.addProperties(getHibernateProperties());
		sessionFactoryBuilder.addAnnotatedClass(Login.class);
		sessionFactoryBuilder.addAnnotatedClass(User.class);
		sessionFactoryBuilder.addAnnotatedClass(ExperimentType.class);
		sessionFactoryBuilder.addAnnotatedClass(PatientTrail.class);
		return sessionFactoryBuilder.buildSessionFactory();
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}

	@Autowired
	@Bean(name = "loginDAO")
	public LoginDAO getLoginDAO(SessionFactory sessionFactory) {
		return new LoginDAOImpl(sessionFactory);
	}

	@Autowired
	@Bean(name = "userDAO")
	public UserDAO getUserDAO(SessionFactory sessionFactory) {
		return new UserDAOImpl(sessionFactory);
	}

	@Autowired
	@Bean(name = "patienttrailDAO")
	public PatientTrailDAO getPatientTrailDAO(SessionFactory sessionFactory) {
		return new PatientTrailDAOImpl(sessionFactory);
	}

	@Autowired
	@Bean(name = "experimentTypeDAO")
	public ExperimentTypeDAO getExperimentTypeDAO(SessionFactory sessionFactory) {
		return new ExperimentTypeDAOImpl(sessionFactory);
	}
}
