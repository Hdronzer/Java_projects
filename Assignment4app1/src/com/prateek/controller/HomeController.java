package com.prateek.controller;

import static com.prateek.utils.Constants.LOGIN_PAGE;
import static com.prateek.utils.Constants.DISPLAY_PAGE;
import static com.prateek.utils.Constants.RESPONSE_FAIL;
import static com.prateek.utils.Constants.RESPONSE_SERVER_DOWN;
import static com.prateek.utils.Constants.RESULT_MESSAGE;
import static com.prateek.utils.Constants.LOGOUT_MESSAGE;
import static com.prateek.utils.Constants.REQUEST_USER;
import static com.prateek.utils.Constants.REQUEST_ERROR_MSG;
import static com.prateek.utils.Constants.SESSION_RECORDS;
import static com.prateek.utils.Constants.SESSION_USER_NAME;
import static com.prateek.utils.Constants.EMPLOYEE_REDIRECT;
import static com.prateek.utils.Constants.ERROR_LOGIN;
import static com.prateek.utils.Constants.ERROR_CONNECTION;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.BindingResult;

import com.prateek.bl.BussinessLogic;
import com.prateek.model.Employee;
import com.prateek.model.User;

/*
 * Main controller for redirecting to home page and perform authentication
 */
@Controller
public class HomeController {
	@Autowired
	private BussinessLogic bl;
	
	@RequestMapping("/")
	public String showLoginPage(Model model) {
		model.addAttribute(REQUEST_USER, new User());
		return LOGIN_PAGE;
	}
	
	@PostMapping("/authentication")
	public String authenticateForm(
			@Valid @ModelAttribute(REQUEST_USER)User user,
			BindingResult bindResult, HttpServletRequest request,
			HttpServletResponse resp, Model model) {
		int response;
		HttpSession session;
		if(bindResult.hasErrors())
			return LOGIN_PAGE;
		
		response = bl.authenticateUser(user);
		
		if(response == RESPONSE_FAIL) {
			request.setAttribute(REQUEST_ERROR_MSG, ERROR_LOGIN);
			return LOGIN_PAGE;
		}else if(response == RESPONSE_SERVER_DOWN) {
			request.setAttribute(REQUEST_ERROR_MSG, ERROR_CONNECTION);
			return LOGIN_PAGE;
		}
		session = request.getSession();
		session.setAttribute(SESSION_USER_NAME, user.getUserName());
		
		return EMPLOYEE_REDIRECT;
	}
	
	@GetMapping("/displayEmployee")
	public String displayEmployeeRecords(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		List<Employee> empList = bl.getEmployees();
		
		if(empList == null)
			request.setAttribute(REQUEST_ERROR_MSG, ERROR_CONNECTION);
		else if(empList.isEmpty())
			request.setAttribute(REQUEST_ERROR_MSG, RESULT_MESSAGE);
		 
		 session.setAttribute(SESSION_RECORDS,empList);
//		 resp.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
//		 resp.setHeader("Pragma","no-cache");
//		 resp.setDateHeader("Expires", 0);
		return DISPLAY_PAGE;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		Enumeration<String> e = session.getAttributeNames();
		
        while (e.hasMoreElements()) {
            session.removeAttribute(e.nextElement());
        }

        removeCookies(request);
		session.invalidate();
		
		request.setAttribute(REQUEST_ERROR_MSG, LOGOUT_MESSAGE);
		model.addAttribute(REQUEST_USER, new User());
		return LOGIN_PAGE;
	}
	
	// helper method to remove cookies after session logout
    private void removeCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (int i = 0; i < cookies.length; i++) {
                cookies[i].setMaxAge(0);
            }
        }
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
