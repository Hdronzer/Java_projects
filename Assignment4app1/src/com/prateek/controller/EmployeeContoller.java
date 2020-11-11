package com.prateek.controller;

import static com.prateek.utils.Constants.EDIT_PAGE;
import static com.prateek.utils.Constants.LOGIN_PAGE;
import static com.prateek.utils.Constants.CREATE_PAGE;
import static com.prateek.utils.Constants.REQUEST_INDEX;
import static com.prateek.utils.Constants.REQUEST_USER;
import static com.prateek.utils.Constants.REQUEST_EMPLOYEE;
import static com.prateek.utils.Constants.EMPLOYEE_REDIRECT;
import static com.prateek.utils.Constants.LOGIN_PAGE_REDIRECT;
import static com.prateek.utils.Constants.REQUEST_ERROR_MSG;
import static com.prateek.utils.Constants.SESSION_RECORDS;
import static com.prateek.utils.Constants.SESSION_USER_NAME;
import static com.prateek.utils.Constants.ERROR_EMP_CREATE;
import static com.prateek.utils.Constants.ERROR_EMP_UPDATE;
import static com.prateek.utils.Constants.ERROR_EMP_DELETE;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prateek.bl.BussinessLogic;
import com.prateek.model.Employee;

/*
 * Controller for redirecting on crud operations on employee table
 */
@Controller
public class EmployeeContoller {
	@Autowired
	private BussinessLogic bl;
	
	@PostMapping("/updateRecord")
	public String updateRecord(HttpServletRequest request, Model model,
			@RequestParam(REQUEST_INDEX)int index) {
		List<Employee> list = (List<Employee>) request.getSession().getAttribute(SESSION_RECORDS);
		if(list == null) // session would have been invalidated
			return LOGIN_PAGE_REDIRECT;
		
		model.addAttribute(REQUEST_EMPLOYEE, new Employee(list.get(index)));
		request.setAttribute(REQUEST_INDEX, index);
		return EDIT_PAGE;
	}
	
	@GetMapping("/deleteRecord")
	public String deleteRecord(HttpServletRequest request, Model model,
			@RequestParam(REQUEST_INDEX)int index) {
		List<Employee> list = (List<Employee>) request.getSession().getAttribute(SESSION_RECORDS);
		if(list == null) // session would have been invalidated
			return LOGIN_PAGE_REDIRECT;
		
		Employee emp = list.get(index);
		if(!bl.deleteEmployee(emp.getEmpCode())) 
			request.setAttribute(REQUEST_ERROR_MSG, ERROR_EMP_DELETE);
			
		return EMPLOYEE_REDIRECT;
	}
	
	@GetMapping("/createRecord")
	public String createRecord(HttpServletRequest request, Model model) {
		String userName = (String) request.getSession().getAttribute(SESSION_USER_NAME);
		
		if(userName == null || userName.isEmpty()) // session would have been invalidated
			return LOGIN_PAGE_REDIRECT;
		
		model.addAttribute(REQUEST_EMPLOYEE, new Employee());
		return CREATE_PAGE;
		
	}
	
	@PostMapping("/processEmployee")
	public String processEmployee(
			@Valid @ModelAttribute(REQUEST_EMPLOYEE)Employee emp,
			BindingResult bindResult, 
			@RequestParam(REQUEST_INDEX)int index, Model model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute(SESSION_USER_NAME);
		
		if(userName == null) // session would have been invalidated
			return LOGIN_PAGE_REDIRECT;
		
		if(bindResult.hasErrors()) {
			request.setAttribute(REQUEST_INDEX, index);
			return EDIT_PAGE;
		}
			
		if(!bl.updateEmployee(emp))
			request.setAttribute(REQUEST_ERROR_MSG, ERROR_EMP_UPDATE);
			
		return EMPLOYEE_REDIRECT;
	}
	
	@PostMapping("/createEmployee")
	public String createEmployee(
			@Valid @ModelAttribute(REQUEST_EMPLOYEE)Employee emp,
			BindingResult bindResult, Model model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute(SESSION_USER_NAME);
		
		if(userName == null) // session would have been invalidated
			return LOGIN_PAGE_REDIRECT;
		
		if(bindResult.hasErrors()) {
			return CREATE_PAGE;
		}
			
		if(!bl.createEmployee(emp))
			request.setAttribute(REQUEST_ERROR_MSG, ERROR_EMP_CREATE);
		
		return EMPLOYEE_REDIRECT;
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
