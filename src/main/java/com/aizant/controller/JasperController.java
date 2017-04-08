package com.aizant.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aizant.DAO.BloodSampleCollectionDAO;
import com.aizant.DAO.StudyDAO;
import com.aizant.DAO.StudyVolunteerDAO;
import com.aizant.model.BloodSampleCollection;
import com.aizant.model.SampleTime;
import com.aizant.model.Study;
import com.aizant.model.StudyVolunteer;

@Controller
public class JasperController {

	@Autowired
	private StudyDAO studyDAO;

	@Autowired
	private BloodSampleCollectionDAO bloodSampleCollectionDao;
	@Autowired
	private StudyVolunteerDAO study_VolunteerDao;
	
	@RequestMapping("/Jasper")
	@Transactional
	public ModelAndView Jasper(@RequestParam String id, ModelMap modelMap) {
		Study study = studyDAO.get(id);
		List<SampleTime> sampleTimes = study.getSampleTime();
		System.out.println("GOT samples?" + sampleTimes);
		modelMap.put("study",studyDAO.get(id));
		modelMap.put("sampleTimes",sampleTimes);

		return new ModelAndView("Jasper");
	}
	@RequestMapping("/records")
	public ModelAndView SampleCollection(@RequestParam String id, ModelMap modelMap) {
		BloodSampleCollection sampleCollection=bloodSampleCollectionDao.get(id);
		modelMap.put("bloodCollection",bloodSampleCollectionDao.get(id));
		modelMap.put("date",sampleCollection.getDate());
		modelMap.put("period",sampleCollection.getPeriod());
		modelMap.put("scanTime",sampleCollection.getScanTime());
		modelMap.put("comments",sampleCollection.getComments());
		modelMap.put("volunteerId",sampleCollection.getVolunteerId());
		modelMap.put("time",sampleCollection.getTime());
		return new ModelAndView("records");
		
	}	
	@RequestMapping("/SampleCollections")
	public ModelAndView SampleCollections(@RequestParam String id, ModelMap modelMap) {
		//BloodSampleCollection study = bloodSampleCollectionDao.get(id);
		modelMap.put("bloodCollection",bloodSampleCollectionDao.get(id));
		return new ModelAndView("SampleCollections");
	
}
}
