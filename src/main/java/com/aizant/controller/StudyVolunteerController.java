package com.aizant.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aizant.DAO.StudyVolunteerDAO;
import com.aizant.Services.IStudyVolunteerService;
import com.aizant.gson.GsonStudyExclusionStrategy;
import com.aizant.model.Study;
import com.aizant.model.StudyVolunteer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class StudyVolunteerController {
	/*
	 * ------------------------------------- DAO declaration
	 * --------------------------------------
	 */
	@Autowired
	private StudyVolunteerDAO studyVolunteerDao;
	
	@Autowired
	private IStudyVolunteerService studyVolunteerService;
	
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
	 * ------------------------------------- View PaatientTrail
	 * --------------------------------------
	 */

	@RequestMapping(value = "view_studyVolunteer", method = RequestMethod.GET)
	@Transactional
	public ModelAndView viewvolunteer(@RequestParam String id, @ModelAttribute StudyVolunteer study_Volunteer) {
		StudyVolunteer exp = studyVolunteerDao.get(id);
		exp.setStudy(exp.getStudy());
		System.out.println("STUDY VOLUNTEER STUDY?" + exp.getStudy());
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
	public ModelAndView edituser(@RequestParam String id, @ModelAttribute("StudyVolunteer") StudyVolunteer study_Volunteer) {
		StudyVolunteer u1 = studyVolunteerDao.get(id);
		return new ModelAndView("edit_studyVolunteer", "study_Volunteer", u1);
	}

	/*
	 * ------------------------------------- Update PaatientTrail
	 * --------------------------------------
	 */
	@RequestMapping(value = "/update_studyVolunteer", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request,
			@Valid @ModelAttribute("StudyVolunteer") StudyVolunteer study_Volunteer) {
		studyVolunteerDao.saveOrUpdate(study_Volunteer);
		return new ModelAndView("display_studyVolunteer");
	}

	/*
	 * ------------------------------------- Delete PaatientTrail
	 * --------------------------------------
	 */
	@RequestMapping(value = "/delete_studyVolunteer", method = RequestMethod.POST)
	public @ResponseBody String deleteexp(@RequestParam String id) {
		studyVolunteerService.delete(id);
		Gson u = new Gson();
		String json = u.toJson(id);
		return json;
	}

	/*
	 * ------------------------------------- Delete PaatientTrail
	 * --------------------------------------
	 */
	@RequestMapping(value = "/study_volunteer", method = RequestMethod.GET)
	@Transactional
	public @ResponseBody String getStudyVolunteer(@RequestParam String id, @RequestParam boolean showBloodSamples) {
		StudyVolunteer studyVolunteer = studyVolunteerService.get(id, showBloodSamples);
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
		String jsonStudyVolunteer = gson.toJson(studyVolunteer);
		
		System.out.println("STUDY VOLUNTEER BLOOD SAMPLE" + studyVolunteer.getBloodSampleCollection().get(0).getId());
		System.out.println("STUDY VOLUNTEER GSON" + studyVolunteer);
		Study study = studyVolunteer.getStudy();
		return "{ \"studyVolunteer\": " +  jsonStudyVolunteer + 
				", \"studyId\":\"" + study.getId() 
				+"\",  \"studyPeriods\":" + study.getPeriods() + "}";
	}
	
	/*
	 * ------------------------------------- Page count PaaientTrail
	 * --------------------------------------
	 */
	@RequestMapping(value = "/pageCount2", method = RequestMethod.GET)
	public @ResponseBody String showPage(@ModelAttribute StudyVolunteer study_Volunteer) {
		List<StudyVolunteer> list;
		long pc = studyVolunteerDao.getPageCount();
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
			@RequestParam(value="", required=false) String filter) {
		List<StudyVolunteer> list;
		if (page > 0) {
			list = studyVolunteerDao.getExperimentByPage(page, 10);
		} else {
			list = studyVolunteerDao.list();
		}
		Gson u = new Gson();
		String json = u.toJson(list);
		System.out.println("NAMES LIST: " + json);
		return json;
	}

}
