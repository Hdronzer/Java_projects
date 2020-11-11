package com.prateek.controller;

import static com.prateek.utils.Constants.LOGIN_PAGE;
import static com.prateek.utils.Constants.SEARCH_PAGE;
import static com.prateek.utils.Constants.ERROR_LOGIN;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.BindingResult;

import com.prateek.bl.BussinessLogic;
import com.prateek.entity.FlightDetails;
import com.prateek.entity.User;

/*
 * Main controller for redirecting to home page and perform authentication
 */
@Controller
public class HomeController {
	@Autowired
	private BussinessLogic bl;
	
	@RequestMapping("/")
	public String showLoginPage(Model model) {
		model.addAttribute("user", new User());
		return LOGIN_PAGE;
	}
	
	@RequestMapping("/authentication")
	public String authenticateForm(
			@Valid @ModelAttribute("user")User user,
			BindingResult bindResult, HttpServletRequest request,
			Model model) {
		if(bindResult.hasErrors())
			return LOGIN_PAGE;
		
		if(!bl.authenticateUser(user)) {
			request.setAttribute("errMessage", ERROR_LOGIN);
			return LOGIN_PAGE;
		}
		
		request.getSession().setAttribute("userName", user.getUserName());
		model.addAttribute("flightDetails", new FlightDetails());
		
		return SEARCH_PAGE;
		
	}
	
	/*
	 * initBinder to convert trim input String
	 * remove leading and trailing white spaces
	 * resolve issue for validation
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		StringTrimmerEditor editor = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, editor);
	}

}
