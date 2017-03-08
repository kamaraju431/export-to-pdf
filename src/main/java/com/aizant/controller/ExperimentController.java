package com.aizant.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aizant.DAO.ExperimentTypeDAO;
import com.aizant.model.ExperimentType;
import com.google.gson.Gson;

// Vedha's comment
@Controller
public class ExperimentController {
	/*
	 * ------------------------------------- DAO declaration
	 * --------------------------------------
	 */
	@Autowired
	private ExperimentTypeDAO experimentDao;
	SessionFactory sessionFactory;

	@ModelAttribute("ExperimentType")
	public ExperimentType createProduct() {
		return new ExperimentType();
	}

	/*
	 * ------------------------------------- Edit Experiment
	 * --------------------------------------
	 */
	@RequestMapping(value = "edit_experimentType", method = RequestMethod.GET)
	public ModelAndView editexperimenttype(@RequestParam int id,
			@ModelAttribute("ExperimentType") ExperimentType experiment_Type) {
		ExperimentType e1 = experimentDao.get(id);
		return new ModelAndView("edit_experimentType", "ExperimentType", e1);
	}

	/*
	 * ------------------------------------- View Experiment
	 * --------------------------------------
	 */
	@RequestMapping(value = "experiment_view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam int id, @ModelAttribute ExperimentType experiment_Type) {
		ExperimentType Type = experimentDao.get(id);
		return new ModelAndView("experiment_view", "experiment_Type", Type);

	}

	/*
	 * ------------------------------------- Update Experiment
	 * --------------------------------------
	 */
	@RequestMapping(value = "/update_experimentType", method = RequestMethod.POST)
	public ModelAndView updateexperimentType(HttpServletRequest request,
			@Valid @ModelAttribute("ExperimentType") ExperimentType experiment_Type, BindingResult result) {
		experimentDao.saveOrUpdate(experiment_Type);
		return new ModelAndView("redirect:/display_experiments");
	}

	/*
	 * ------------------------------------- Store Experiment
	 * --------------------------------------
	 */
	@RequestMapping(value = "/store_experiments", method = RequestMethod.POST)
	public ModelAndView store(HttpServletRequest request,
			@Valid @ModelAttribute("ExperimentType") ExperimentType experiment_Type, BindingResult result) {
		System.out.println("values are successfully inserted");
		experimentDao.saveOrUpdate(experiment_Type);
		return new ModelAndView("redirect:/display_experiments");
	}

	/*
	 * ------------------------------------- Add Experiment
	 * --------------------------------------
	 */
	@RequestMapping("/add_experiment")
	public ModelAndView display4() {
		ModelAndView m4 = new ModelAndView("add_experiment");
		return m4;
	}

	/*
	 * ------------------------------------- Page count Experiment
	 * --------------------------------------
	 */

	@RequestMapping(value = "/pageCount1", method = RequestMethod.GET)
	public @ResponseBody String showPage(@ModelAttribute ExperimentType experiment_Type) {
		List<ExperimentType> list;
		System.out.println("HEREEEEE Getting page3");

		long pc = experimentDao.getPageCount();
		Gson u = new Gson();
		String json = u.toJson(pc);
		return json;

	}

	/*
	 * ------------------------------------- view all Experiment
	 * --------------------------------------
	 */
	@RequestMapping("/display_experiments")
	public String addmobile(HttpServletRequest request,
			@Valid @ModelAttribute("ExperimentType") ExperimentType experiment_Type, BindingResult result) {
		if (result.hasErrors()) {
			return "add_experiment";
		}
		return "display_experiments";
	}

	/*
	 * ------------------------------------- Delete Experiment
	 * --------------------------------------
	 */

	@RequestMapping(value = "/delete_experiment", method = RequestMethod.POST)
	public @ResponseBody String deleteexperiment(@RequestParam int experimentTypeId) {
		System.out.println("hello " + experimentTypeId);
		experimentDao.delete(experimentTypeId);

		Gson u = new Gson();
		String json = u.toJson(experimentTypeId);
		return json;
	}

	/*
	 * ------------------------------------- List (retrieving) Experiment
	 * --------------------------------------
	 */
	@RequestMapping(value = "/list1", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String showList2(@RequestParam int page, @ModelAttribute ExperimentType experiment_Type) {
		List<ExperimentType> list;

		System.out.println("Page number " + page);
		if (page > 0) {

			System.out.println("Page Count" + experimentDao.getPageCount());
			System.out.println("Getting page2");
			list = experimentDao.getExperimentTypeByPage(page, 10);
		} else {
			System.out.println("Getting all experiments");
			list = experimentDao.list();
		}
		Gson u = new Gson();
		String json = u.toJson(list);
		return json;
	}

}
