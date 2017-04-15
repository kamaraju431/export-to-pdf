package com.aizant.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aizant.DAO.SampleTimeDAO;
import com.aizant.model.SampleTime;
import com.google.gson.Gson;

@Controller
public class SampleTimeController {
	/*
	 * ------------------------------------- DAO declaration
	 * --------------------------------------
	 */
	@Autowired
	private SampleTimeDAO sampleTimeDao;
	
	SessionFactory sessionFactory;
	/*
	 * ------------------------------------- edit study
	 * --------------------------------------
	 */

	
	@RequestMapping(value = "sampleTime", method = RequestMethod.GET)
	public @ResponseBody String getStudy(@RequestParam String id){
		SampleTime sampleTime=sampleTimeDao.get(id);
	
		Gson u = new Gson();
		String json = u.toJson(sampleTime);
		return json;
	}
	
	/*
	 * ------------------------------------- update study
	 * --------------------------------------
	 */

	@RequestMapping(value = "/update_SampleTime", method = RequestMethod.POST)
	public ModelAndView updateexperimentType(HttpServletRequest request,
			@Valid @ModelAttribute("SampleTime") SampleTime sampleTime, BindingResult result) {
		sampleTimeDao.saveOrUpdate(sampleTime);
		return new ModelAndView("redirect:/display_study");
	}
	/*
	 * ------------------------------------- store study
	 * --------------------------------------
	 */
	
	@RequestMapping(value="/store_SampleTime", method=RequestMethod.POST)
	public ModelAndView store(HttpServletRequest request,
			@RequestBody SampleTime sampleTime, BindingResult result) {
		System.out.print("values inserted");
		sampleTimeDao.saveOrUpdate(sampleTime);
		return new ModelAndView("redirect:/display_study");
	}
	
	@ModelAttribute("SampleTime")
	public SampleTime create() {
		return new SampleTime();
	}

		

	@Transactional
	@RequestMapping(value = "/list5", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String showList2(@ModelAttribute SampleTime sampleTime) {
		List<SampleTime> list=new ArrayList<SampleTime>();
		
		Gson u = new Gson();
		String json = u.toJson(list);
		return json;
	}
	/*
	 * ------------------------------------- View BloodSampleCollectionRecord All Users
	 * --------------------------------------
	 */
	
	
}
