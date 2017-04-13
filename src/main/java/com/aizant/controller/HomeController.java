package com.aizant.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.aizant.DAO.UserDAO;
import com.aizant.Services.IUserService;
import com.aizant.model.User;
import com.google.gson.Gson;

/**
 * The Home Controller implements that simply for user modules like register and
 * login and authuntication.here we are using Request mapping to the URL. here
 * we are getting data by using list, adding users and updating,edit,delete
 * logout functionality
 */
@Controller
public class HomeController {
	/*
	 * ------------------------------------- DAO declaration
	 * --------------------------------------
	 */

	@Autowired
	private UserDAO userDao;

	@Autowired
	private IUserService userService;

	SessionFactory sessionFactory;

	/*
	 * ------------------------------------- home Page
	 * --------------------------------------
	 */
	@RequestMapping("/")
	public ModelAndView display() {
		ModelAndView m4 = new ModelAndView("Login");
		return m4;
	}

	/*
	 * ------------------------------------- View All Users
	 * --------------------------------------
	 */
	@RequestMapping("/display_user")
	public ModelAndView retriveRecords(){
		ModelAndView m1 = new ModelAndView("display_user");

		return m1;
	}

	/*
	 * ------------------------------------- Edit User
	 * --------------------------------------
	 */
	@RequestMapping(value = "edit_user", method = RequestMethod.GET)
	public ModelAndView edituser(@RequestParam String id, @ModelAttribute("User") User user) {
		User u1 = userDao.get(id);
		return new ModelAndView("edit_user", "user", u1);
	}
	// @RequestMapping("/edit_user")
	// public String editUser() {
	// return "edit_user";
	// }

	/*
	 * ------------------------------------- Update User
	 * --------------------------------------
	 */

	@RequestMapping(value = "/update_user", method = RequestMethod.POST)
	public ModelAndView updateuser(HttpServletRequest request, @RequestParam String id,
			@ModelAttribute("User") User user) {
		System.out.println(user.getId());
		user.setId(id);
		userDao.saveOrUpdate(user);
		return new ModelAndView("redirect:/display_user");
	}
	/*
	 * ------------------------------------- Store User
	 * --------------------------------------
	 */

	@RequestMapping(value = "/store_user", method = RequestMethod.POST)
	public ModelAndView addUser(@Valid @ModelAttribute("User") User user, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println("hi");

			return new ModelAndView("redirect:/add_user");
		}

		userService.registerNewUserAccount(user);
		userDao.saveOrUpdate(user);
		return new ModelAndView("redirect:/display_user");
	}
	/*
	 * ------------------------------------- Page Count
	 * --------------------------------------
	 */

	@RequestMapping(value = "/pageCount", method = RequestMethod.GET)
	public @ResponseBody String showPage(@ModelAttribute User user) {
		List<User> list;
		System.out.println("HEREEEEE Getting page3");

		long pc = userDao.getPageCount();
		Gson u = new Gson();
		String json = u.toJson(pc);
		return json;

	}

	/*
	 * ------------------------------------- List User(retrieving)
	 * --------------------------------------
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String showList2(@RequestParam int page, @ModelAttribute User user,
			@RequestParam(value = "", required = false) String filter) {
		List<User> list;

		System.out.println("Page number " + page);
		if (page > 0) {

			System.out.println("Page Count" + userDao.getPageCount());
			System.out.println("Getting page2");
			list = userDao.getUserByPage(page, 10);
		} else {
			System.out.println("Getting all users");
			list = userDao.list();
		}
		Gson u = new Gson();
		String json = u.toJson(list);
		return json;
	}

	/*
	 * ------------------------------------- Add User
	 * --------------------------------------
	 */
	@RequestMapping("/add_user")
	public ModelAndView display4() {
		ModelAndView m4 = new ModelAndView("add_user");
		return m4;
	}

	@ModelAttribute("User")
	public User createProduct() {
		return new User();
	}

	/*
	 * ------------------------------------- Login Error(fail to login)
	 * --------------------------------------
	 */
	@RequestMapping(value = "/fail2login", method = RequestMethod.GET)
	public ModelAndView loginerror(ModelMap model) {
		System.out.println("hello...........................................");

		return new ModelAndView("Login", "error", true);
	}

	/*
	 * ------------------------------------- Welcome Page
	 * --------------------------------------
	 */
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView checkUserOne(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {

		System.out.println("hi");
		/*
		 * ------------------------------------- Admin Page
		 * --------------------------------------
		 */
		if (request.isUserInRole("ROLE_ADMIN")) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String str = auth.getName(); // get logged in username
			session = request.getSession(true);
			session.setAttribute("loggedInUser", str);

			ModelAndView m1 = new ModelAndView("Admin");
			return m1;
		} else {
			/*
			 * ------------------------------------- Login User Page
			 * --------------------------------------
			 */
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String str = auth.getName(); // get logged in username
			session = request.getSession(true);
			session.setAttribute("loggedInUser", str);
			// ModelAndView m2 = new ModelAndView("display_study");
			return new ModelAndView("redirect:/display_study");
		}

	}

	/*
	 * ------------------------------------- View User
	 * --------------------------------------
	 */
	@RequestMapping(value = "view_user", method = RequestMethod.GET)
	public ModelAndView viewuser(@RequestParam String id, @ModelAttribute User user) {
		System.out.println(id);
		System.out.println(user.getId());
		User use = userDao.get(id);
		return new ModelAndView("view_user", "user", use);
		// return new ModelAndView("viewproduct");
	}

	/*
	 * ------------------------------------- Delete User
	 * --------------------------------------
	 */
	@RequestMapping(value = "/deleteuser", method = RequestMethod.POST)
	public @ResponseBody String deleteuser(@RequestParam String userId) {
		System.out.println("hello " + userId);

		userDao.delete(userId);
		Gson u = new Gson();
		String json = u.toJson(userId);
		return json;
	}

	/*
	 * ------------------------------------- Log out User
	 * --------------------------------------
	 */
	@RequestMapping("logoutsuccess")
	public ModelAndView logoutpage() {
		ModelAndView mv9 = new ModelAndView("logoutsuccess");
		return mv9;
	}

	@RequestMapping(value = "/Logout", method = RequestMethod.GET)
	public void logout(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws ServletException, IOException {
		HttpSession newsession = request.getSession(false);
		if (newsession != null) {
			newsession.invalidate();

		}
		response.sendRedirect("j_spring_security_logout");

	}
	
	

}
