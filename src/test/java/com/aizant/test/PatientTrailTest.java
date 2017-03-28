package com.aizant.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.aizant.DAO.StudyVolunteerDAO;
import com.aizant.model.StudyVolunteer;


public class PatientTrailTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.aizant");
		context.refresh();
		StudyVolunteerDAO studyVolunteerDAO = (StudyVolunteerDAO) context.getBean("patienttrailDAO");
		StudyVolunteer studyVolunteer = (StudyVolunteer) context.getBean("patienttrail");
		
	
		
		studyVolunteerDAO.save(studyVolunteer);
		
		




	}

}
