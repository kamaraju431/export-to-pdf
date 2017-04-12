package com.aizant.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aizant.DAO.BloodSampleCollectionDAO;
import com.aizant.DAO.StudyVolunteerDAO;
import com.aizant.model.BloodSampleCollection;
import com.aizant.model.StudyVolunteer;


@Controller
public class TestController {
	@Autowired
	private StudyVolunteerDAO study_VolunteerDao;
	@Autowired
	private BloodSampleCollectionDAO bloodSampleCollectionDao;
	
	@RequestMapping(value="/test_cascade",method=RequestMethod.GET)
	@Transactional
	public @ResponseBody String store() {
		String id = "7552b208-7cef-4702-b51b-7c9e70cd26f9";
		BloodSampleCollection bloodSample = new BloodSampleCollection();
		bloodSampleCollectionDao.saveOrUpdate(bloodSample);

		StudyVolunteer studyVolunteer = study_VolunteerDao.get(id);
		List<BloodSampleCollection> samples = new ArrayList<BloodSampleCollection>();
		samples.add(bloodSample);
		studyVolunteer.setBloodSampleCollection(samples);
		study_VolunteerDao.save(studyVolunteer);
		
		StudyVolunteer studyVolunteer2 = study_VolunteerDao.get(id);
		List<BloodSampleCollection> samples2 = studyVolunteer2.getBloodSampleCollection();
		System.out.println("SAMPLESS " + samples2.size());
		
		StudyVolunteer studyVolunteer3 = study_VolunteerDao.get(id);
		study_VolunteerDao.save(studyVolunteer3);
		
		StudyVolunteer studyVolunteer4 = study_VolunteerDao.get(id);
		List<BloodSampleCollection> samples3 = studyVolunteer4.getBloodSampleCollection();
		System.out.println("SAMPLESS 3 " + samples3.size());		
		return null;
	}

}
