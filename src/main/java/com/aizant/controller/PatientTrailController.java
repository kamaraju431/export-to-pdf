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

import com.aizant.DAO.PatientTrailDAO;
import com.aizant.model.PatientTrail;
import com.aizant.model.Login;
import com.aizant.model.User;
import com.google.gson.Gson;

@Controller
public class PatientTrailController {
	/*
	 * ------------------------------------- DAO declaration
	 * --------------------------------------
	 */
	@Autowired
	private PatientTrailDAO patientDao;

	@ModelAttribute("PatientTrail")
	public PatientTrail createExperiment() {
		return new PatientTrail();
	}
	/*
	 * ------------------------------------- Add PaatientTrail
	 * --------------------------------------
	 */

	@RequestMapping("/add_patientTrail")
	public ModelAndView display5() {
		ModelAndView m4 = new ModelAndView("add_patientTrail");
		return m4;
	}

	/*
	 * ------------------------------------- Store PaatientTrail
	 * --------------------------------------
	 */
	@RequestMapping("/store_patientTrail")
	public String addexpriment(HttpServletRequest request,
			@Valid @ModelAttribute("PatientTrail") PatientTrail patient_Trail, BindingResult result) {

		String s = "what";
		s.split(",");
		String[] volunteerId = patient_Trail.getVolunteerId().split(",");
		String[] volunteerName = patient_Trail.getVolunteerName().split(",");
		System.out.println(volunteerId == volunteerName);
		for (int i = 0; i < volunteerId.length; i++) {
			PatientTrail newexp = new PatientTrail();
			System.out.println("hai*******");
			newexp.setVolunteerId(volunteerId[i]);

			newexp.setVolunteerName(volunteerName[i]);

			newexp.setDate(patient_Trail.getDate());

			newexp.setExperimentType(patient_Trail.getExperimentType());
			patientDao.save(newexp);
		}

		if (result.hasErrors()) {
			return "add_patientTrail";
		}

		return "display_patientTrail";
	}

	/*
	 * ------------------------------------- View PaatientTrail
	 * --------------------------------------
	 */

	@RequestMapping(value = "view_patientTrail", method = RequestMethod.GET)
	public ModelAndView viewvolunteer(@RequestParam int id, @ModelAttribute PatientTrail patient_Trail) {
		System.out.println(id);
		System.out.println(patient_Trail.getId());
		PatientTrail exp = patientDao.get(id);
		return new ModelAndView("view_patientTrail", "patient_Trail", exp);

	}

	/*
	 * ------------------------------------- view All PaatientTrails
	 * --------------------------------------
	 */
	@RequestMapping("/display_patientTrail")
	public ModelAndView retriveRecord() {
		ModelAndView m1 = new ModelAndView("display_patientTrail");
		return m1;
	}

	/*
	 * ------------------------------------- Edit PaatientTrail
	 * --------------------------------------
	 */
	@RequestMapping(value = "edit_patientTrail", method = RequestMethod.GET)
	public ModelAndView edituser(@RequestParam int id, @ModelAttribute("PatientTrail") PatientTrail patient_Trail) {
		System.out.println("hello kamu............");
		PatientTrail u1 = patientDao.get(id);
		System.out.println("hai.............");

		return new ModelAndView("edit_patientTrail", "patient_Trail", u1);
	}

	/*
	 * ------------------------------------- Update PaatientTrail
	 * --------------------------------------
	 */
	@RequestMapping(value = "/update_patientTrail", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request,
			@Valid @ModelAttribute("PatientTrail") PatientTrail patient_Trail) {
		System.out.println("UPDATINGGGGG");
		patientDao.Update(patient_Trail);
		patientDao.save(patient_Trail);
		return new ModelAndView("display_patientTrail");
	}

	/*
	 * ------------------------------------- Delete PaatientTrail
	 * --------------------------------------
	 */
	@RequestMapping(value = "/delete_patientTrail", method = RequestMethod.POST)
	public @ResponseBody String deleteexp(@RequestParam int id) {
		System.out.println("hello " + id);
		patientDao.delete(id);

		Gson u = new Gson();
		String json = u.toJson(id);
		return json;
	}

	/*
	 * ------------------------------------- Page count PaatientTrail
	 * --------------------------------------
	 */
	@RequestMapping(value = "/pageCount2", method = RequestMethod.GET)
	public @ResponseBody String showPage(@ModelAttribute PatientTrail patient_Trail) {
		List<PatientTrail> list;
		System.out.println("HEREEEEE Getting page3");

		long pc = patientDao.getPageCount();
		Gson u = new Gson();
		String json = u.toJson(pc);
		return json;

	}

	/*
	 * ------------------------------------- List(retrieving) PaatientTrail
	 * --------------------------------------
	 */
	@RequestMapping(value = "/list2", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String showList2(@RequestParam int page, @ModelAttribute PatientTrail patient_Trail) {
		List<PatientTrail> list;

		System.out.println("Page number " + page);
		if (page > 0) {

			System.out.println("Page Count" + patientDao.getPageCount());
			System.out.println("Getting page2");
			list = patientDao.getExperimentByPage(page, 10);
		} else {
			System.out.println("Getting all patients");
			list = patientDao.list();
		}
		Gson u = new Gson();
		String json = u.toJson(list);
		return json;
	}

}
