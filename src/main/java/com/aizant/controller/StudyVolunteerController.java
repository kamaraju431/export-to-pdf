package com.aizant.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aizant.DAO.StudyVolunteerDAO;
import com.aizant.model.StudyVolunteer;
import com.google.gson.Gson;

/**
 * The StudyVolunteer Controller implements that simply for study volunteer modules like adding
 * volunteers.here we are using Request mapping to the URL. here we are getting
 * data by using list2, adding study volunteers and updating,edit,delete
 * 
 */
@Controller
public class StudyVolunteerController {
	/*
	 * ------------------------------------- DAO declaration
	 * --------------------------------------
	 */
	@Autowired
	private StudyVolunteerDAO study_VolunteerDao;

	@ModelAttribute("StudyVolunteer")
	public StudyVolunteer createExperiment() {
		return new StudyVolunteer();
	}
	/*
	 * ------------------------------------- Add PaatientTrail
	 * --------------------------------------
	 */

	@RequestMapping("/add_studyVolunteer")
	public ModelAndView display5() {
		ModelAndView m4 = new ModelAndView("add_studyVolunteer");
		return m4;
	}

	/*
	 * ------------------------------------- Store PaatientTrail
	 * --------------------------------------
	 */
	@RequestMapping("/store_studyVolunteer")
	public ModelAndView addexpriment(HttpServletRequest request,
			@Valid @ModelAttribute("StudyVolunteer") StudyVolunteer study_Volunteer, BindingResult result) {

		String s = "what";
		s.split(",");
		String[] volunteerId = study_Volunteer.getVolunteerId().split(",");
		String[] volunteerName = study_Volunteer.getVolunteerName().split(",");
		System.out.println(volunteerId == volunteerName);
		for (int i = 0; i < volunteerId.length; i++) {
			StudyVolunteer newexp = new StudyVolunteer();
			System.out.println("hai*******");
			newexp.setVolunteerId(volunteerId[i]);

			newexp.setVolunteerName(volunteerName[i]);

			study_VolunteerDao.save(newexp);
		}

		if (result.hasErrors()) {
			return new ModelAndView("redirect:/add_studyVolunteer");
		}

		return new ModelAndView("redirect:/display_studyVolunteer");
	}

	/*
	 * ------------------------------------- View PaatientTrail
	 * --------------------------------------
	 */

	@RequestMapping(value = "view_studyVolunteer", method = RequestMethod.GET)
	public ModelAndView viewvolunteer(@RequestParam String id, @ModelAttribute StudyVolunteer study_Volunteer) {
		System.out.println(id);
		System.out.println(study_Volunteer.getId());
		StudyVolunteer exp = study_VolunteerDao.get(id);
		return new ModelAndView("view_studyVolunteer", "study_Volunteer", exp);

	}

	/*
	 * ------------------------------------- view All PaatientTrails
	 * --------------------------------------
	 */
	@RequestMapping("/display_studyVolunteer")
	public ModelAndView retriveRecord() throws Exception {
		ModelAndView m1 = new ModelAndView("display_studyVolunteer");

		return m1;
	}

	/*
	 * ------------------------------------- Edit PaatientTrail
	 * --------------------------------------
	 */
	@RequestMapping(value = "edit_studyVolunteer", method = RequestMethod.GET)
	public ModelAndView edituser(@RequestParam String id,
			@ModelAttribute("StudyVolunteer") StudyVolunteer study_Volunteer) {
		System.out.println("hello kamu............");
		StudyVolunteer u1 = study_VolunteerDao.get(id);
		System.out.println("hai.............");

		return new ModelAndView("edit_studyVolunteer", "study_Volunteer", u1);
	}

	/*
	 * ------------------------------------- Update PaatientTrail
	 * --------------------------------------
	 */
	@RequestMapping(value = "/update_studyVolunteer", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request,
			@Valid @ModelAttribute("StudyVolunteer") StudyVolunteer study_Volunteer) {
		System.out.println("UPDATINGGGGG");
		study_VolunteerDao.Update(study_Volunteer);
		study_VolunteerDao.save(study_Volunteer);
		return new ModelAndView("display_studyVolunteer");
	}

	/*
	 * ------------------------------------- Delete PaatientTrail
	 * --------------------------------------
	 */
	@RequestMapping(value = "/delete_studyVolunteer", method = RequestMethod.POST)
	public @ResponseBody String deleteexp(@RequestParam String id) {
		System.out.println("hello " + id);
		study_VolunteerDao.delete(id);

		Gson u = new Gson();
		String json = u.toJson(id);
		return json;
	}

	/*
	 * ------------------------------------- Page count PaatientTrail
	 * --------------------------------------
	 */
	@RequestMapping(value = "/pageCount2", method = RequestMethod.GET)
	public @ResponseBody String showPage(@ModelAttribute StudyVolunteer study_Volunteer) {
		List<StudyVolunteer> list;
		System.out.println("HEREEEEE Getting page3");

		long pc = study_VolunteerDao.getPageCount();
		System.out.println("result" + study_VolunteerDao.getPageCount());
		Gson u = new Gson();
		String json = u.toJson(pc);
		return json;

	}

	/*
	 * ------------------------------------- List(retrieving) PaatientTrail
	 * --------------------------------------
	 */
	@RequestMapping(value = "/list2", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String showList2(@RequestParam int page, @ModelAttribute StudyVolunteer study_Volunteer,
			@RequestParam(value = "", required = false) String filter) {
		List<StudyVolunteer> list;

		System.out.println("Page number " + page);
		if (page > 0) {

			System.out.println("Page Count" + study_VolunteerDao.getPageCount());
			System.out.println("Getting page2");
			list = study_VolunteerDao.getExperimentByPage(page, 10);
		} else {
			System.out.println("Getting all patients");
			list = study_VolunteerDao.list();
		}
		Gson u = new Gson();
		String json = u.toJson(list);
		System.out.println("NAMES LIST: " + json);
		return json;
	}

}
