package com.aizant.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aizant.DAO.StudyDAO;
import com.aizant.model.Study;
import com.google.gson.Gson;

@Controller
public class StudyController {
	/*
	 * ------------------------------------- DAO declaration
	 * --------------------------------------
	 */
	@Autowired
	private StudyDAO studyDao;
	
	SessionFactory sessionFactory;
	/*
	 * ------------------------------------- edit study
	 * --------------------------------------
	 */

	@RequestMapping(value = "edit_study", method = RequestMethod.GET)
	public ModelAndView studytype(@RequestParam String id,
			@ModelAttribute("Study") Study study) {
	        Study e1 = studyDao.get(id);
		return new ModelAndView("edit_study", "study", e1);
	}

	
	@Transactional
	@RequestMapping(value = "study", method = RequestMethod.GET)
	public @ResponseBody String getStudy(@RequestParam String id){
		System.out.println("GOT THIS FAR 1" + id);
		Study study=studyDao.get(id);
		System.out.println("GOT THIS FAR 2" + study.getSampleTime());

		Gson u = new Gson();
		String json = u.toJson(study);
		return json;
	}
	/*
	 * ------------------------------------- View study
	 * --------------------------------------
	 */
	@RequestMapping(value = "view_study", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam String id,
		@ModelAttribute Study study) {
		Study s1  = studyDao.get(id);
		System.out.println("VIEW STUDYYYY" + study);
		return new ModelAndView("view_study","study",s1);
		
	}
	/*
	 * ------------------------------------- update study
	 * --------------------------------------
	 */

	@RequestMapping(value = "/update_study", method = RequestMethod.POST)
	public ModelAndView updateStudyType(HttpServletRequest request,
			@Valid @ModelAttribute("Study") Study study, BindingResult result) {
		studyDao.saveOrUpdate(study);
		return new ModelAndView("redirect:/display_study");
	}
	/*
	 * ------------------------------------- store study
	 * --------------------------------------
	 */
	
	@RequestMapping(value="/store_study", method=RequestMethod.POST)
	public ModelAndView store(HttpServletRequest request,
			@RequestBody Study study, BindingResult result) {
		System.out.println("store study"+study.getSampleTime());
		studyDao.saveOrUpdate(study);
		return new ModelAndView("redirect:/display_study");
	}
	
	/*
	 * ------------------------------------- add study
	 * --------------------------------------
	 */
	@RequestMapping("/add_study")
	public ModelAndView display4() {
		ModelAndView m4 = new ModelAndView("add_study");
		return m4;
	}
	


	@ModelAttribute("Study")
	public Study createProduct() {
		return new Study();
	}

	/*
	 * ------------------------------------- page count
	 * --------------------------------------
	 */
	@RequestMapping(value = "/pageCount3", method = RequestMethod.GET)
	public @ResponseBody String showPage(@ModelAttribute Study study) {
		List<Study> list;
		long pc = studyDao.getPageCount();
		Gson u = new Gson();
		String json = u.toJson(pc);
		return json;

	}

	/*
	 * ------------------------------------- View All study
	 * --------------------------------------
	 */

	@RequestMapping("/display_study")
	public String addmobile(HttpServletRequest request,
			@Valid @ModelAttribute("Study") Study study, BindingResult result) {
		return "display_study";
	}
	/*
	 * ------------------------------------- delete study
	 * --------------------------------------
	 */
	@RequestMapping(value = "/deleteStudy", method = RequestMethod.POST)
	public @ResponseBody String deleteStudy(@RequestParam String studyTypeId) {
		studyDao.delete(studyTypeId);

		Gson u = new Gson();
		String json = u.toJson(studyTypeId);
		return json;
	}
	/*
	 * -------------------------------------page count
	 * --------------------------------------
	 */

	@Transactional
	@RequestMapping(value = "/list3", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String showList2(@RequestParam int page, @ModelAttribute Study study) {
		List<Study> list;
		if (page > 0) {
			list = studyDao.getStudyByPage(page, 10);
		} else {
			list = studyDao.list();
		}
		Gson u = new Gson();
		String json = u.toJson(list);
		return json;
	}
	/*
	 * ------------------------------------- View BloodSampleCollectionRecord All Users
	 * --------------------------------------
	 */
	@RequestMapping("Blood_Sample_Collection_Record")
	public ModelAndView display() {
		ModelAndView m4 = new ModelAndView("BloodSampleCollectionRecord");
		return m4;
	}
	/*	@RequestMapping(value="Jasper", method = RequestMethod.GET)
	public ModelAndView Jasper(@RequestParam String id, ModelMap modelMap,@ModelAttribute("Study") Study study) {
		modelMap.put("study",studyDao.get(id));
		  Study e = studyDao.get(id);
		return new ModelAndView("Jasper","study",e);
	}*/
	
	
}
