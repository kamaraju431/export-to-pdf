package com.aizant.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aizant.DAO.StudyDAO;
import com.aizant.model.SampleTime;
import com.aizant.model.Study;

@Controller
public class JasperController {

	@Autowired
	private StudyDAO studyDAO;

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
	@RequestMapping("/SampleCollections")
	public ModelAndView SampleCollections(@RequestParam String id, ModelMap modelMap) {
		modelMap.put("study",studyDAO.get(id));
		return new ModelAndView("SampleCollections");
	}
}
