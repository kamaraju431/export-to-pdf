package com.aizant.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.aizant.DAO.PatientTrailDAO;
import com.aizant.model.PatientTrail;


public class PatientTrailTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.aizant");
		context.refresh();
		PatientTrailDAO patientTrailDAO = (PatientTrailDAO) context.getBean("patienttrailDAO");
		PatientTrail patientTrail = (PatientTrail) context.getBean("patienttrail");
		
	
		
		patientTrailDAO.save(patientTrail);
		
		




	}

}
