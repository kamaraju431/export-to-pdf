package com.aizant.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.aizant.DAO.UserDAO;
import com.aizant.model.User;


public class UserTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.aizant");
		context.refresh();
		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		User user = (User) context.getBean("user");
		
	
		
		userDAO.saveOrUpdate(user);
		
		

		/*if (userDAO.get("sdfsf") == null) {
			System.out.println("User does not exist");
		} else {
			System.out.println("User exist.....the details are..");
			System.out.println();
		}*/



	}

}
