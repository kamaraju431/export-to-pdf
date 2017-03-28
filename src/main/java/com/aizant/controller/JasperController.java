package com.aizant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;


import com.aizant.DAO.StudyDAO;
import com.aizant.DAO.StudyVolunteerDAO;

import com.aizant.model.Study;
import com.aizant.model.StudyVolunteer;

@Controller
public class JasperController {
	@Autowired
	private StudyVolunteerDAO sv;
	
	@Autowired
	private StudyDAO studyDAO;

	@RequestMapping("/Jasper")
	public ModelAndView Jasper(@RequestParam int id, ModelMap modelMap) {
		Study s1=studyDAO.get(id);
	
		modelMap.put("study",studyDAO.get(id));
		return new ModelAndView("Jasper");
	}
}
