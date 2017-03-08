package com.aizant.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.aizant.DAO.LoginDAO;
import com.aizant.model.Login;


public class LoginTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.aizant");
		context.refresh();
		LoginDAO loginDAO = (LoginDAO) context.getBean("loginDAO");
		Login login = (Login) context.getBean("login");
	
		loginDAO.save(login);
		loginDAO.Update(login);
		

		


	}

}
