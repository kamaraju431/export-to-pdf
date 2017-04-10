package com.aizant.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aizant.DAO.StudyDAO;
import com.aizant.DAO.StudyVolunteerDAO;
import com.aizant.model.Study;
import com.google.gson.Gson;

/**
 * The Study Controller implements that simply for study modules like adding
 * experiments.here we are using Request mapping to the URL. here we are getting
 * data by using list3, adding study and updating,edit,delete
 * 
 */
@Controller
public class StudyController {
	@Autowired
	private StudyDAO studyDao;

	SessionFactory sessionFactory;

	@RequestMapping(value = "edit_study", method = RequestMethod.GET)
	public ModelAndView editexperimenttype(@RequestParam String id, @ModelAttribute("Study") Study study) {
		Study e1 = studyDao.get(id);
		return new ModelAndView("edit_study", "study", e1);
	}

	@RequestMapping(value = "study", method = RequestMethod.GET)
	public @ResponseBody String getStudy(@RequestParam String id) {
		Study study = studyDao.get(id);

		Gson u = new Gson();
		String json = u.toJson(study);
		return json;
	}

	@RequestMapping(value = "view_study", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam String id, @ModelAttribute Study study) {
		System.out.println("lazy");
		study = studyDao.get(id);
		System.out.println("eager");
		return new ModelAndView("view_study", "study", study);

	}

	@RequestMapping(value = "/update_study", method = RequestMethod.POST)
	public ModelAndView updateexperimentType(HttpServletRequest request, @Valid @ModelAttribute("Study") Study study,
			BindingResult result) {
		studyDao.saveOrUpdate(study);
		return new ModelAndView("redirect:/display_study");
	}

	@RequestMapping(value = "/store_study", method = RequestMethod.POST)
	public ModelAndView store(HttpServletRequest request, @RequestBody Study study, BindingResult result) {

		studyDao.saveOrUpdate(study);
		System.out.println("values are successfully inserted");
		return new ModelAndView("redirect:/display_study");
	}

	@RequestMapping("/add_study")
	public ModelAndView display4() {
		ModelAndView m4 = new ModelAndView("add_study");
		return m4;
	}

	@ModelAttribute("Study")
	public Study createProduct() {
		return new Study();
	}

	@RequestMapping(value = "/pageCount3", method = RequestMethod.GET)
	public @ResponseBody String showPage(@ModelAttribute Study study) {
		List<Study> list;
		System.out.println("HEREEEEE Getting page3");

		long pc = studyDao.getPageCount();
		Gson u = new Gson();
		String json = u.toJson(pc);
		return json;

	}

	@RequestMapping("/display_study")
	public String addmobile(HttpServletRequest request, @Valid @ModelAttribute("Study") Study study,
			BindingResult result) {
		return "display_study";
	}

	@RequestMapping(value = "/deleteexperiment", method = RequestMethod.POST)
	public @ResponseBody String deleteexperiment(@RequestParam String experimentTypeId) {
		System.out.println("hello " + experimentTypeId);
		studyDao.delete(experimentTypeId);

		Gson u = new Gson();
		String json = u.toJson(experimentTypeId);
		return json;
	}

	@RequestMapping(value = "/list3", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String showList2(@RequestParam int page, @ModelAttribute Study study) {
		List<Study> list;

		System.out.println("Page number " + page);
		if (page > 0) {

			System.out.println("Page Count" + studyDao.getPageCount());
			System.out.println("Getting page2");
			list = studyDao.getStudyByPage(page, 10);
		} else {
			System.out.println("Getting all Studies");
			list = studyDao.list();
		}
		Gson u = new Gson();
		String json = u.toJson(list);
		return json;
	}

}
