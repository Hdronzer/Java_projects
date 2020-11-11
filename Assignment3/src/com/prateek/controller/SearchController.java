package com.prateek.controller;

import static com.prateek.utils.Constants.SEARCH_PAGE;
import static com.prateek.utils.Constants.DISPLAY_PAGE;
import static com.prateek.utils.Constants.ERROR_LOGIN;
import static com.prateek.utils.Constants.LOGIN_PAGE;
import static com.prateek.utils.Constants.RESULT_MESSAGE;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.prateek.bl.BussinessLogic;
import com.prateek.entity.FlightDetails;
import com.prateek.entity.User;

/*
 * Controller for redirecting to search result for user input
 */
@Controller
public class SearchController {
	@Autowired
	private BussinessLogic bl;

	@RequestMapping("/processInput")
	public String processUserInput(
			@Valid @ModelAttribute("flightDetails")FlightDetails details,
			BindingResult bindResult, HttpServletRequest request) {
		List<FlightDetails> list;
		
		if(bindResult.hasErrors())
			return SEARCH_PAGE;
		
		list = bl.getFlightDetails(details);
		
		if(list == null || list.isEmpty()) {
			request.setAttribute("result",RESULT_MESSAGE );
			return SEARCH_PAGE;
		}

		request.setAttribute("records",list);
		
		return DISPLAY_PAGE;
	}
	
	@RequestMapping("/redirect")
	public String searchRedirect(Model model) {
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
