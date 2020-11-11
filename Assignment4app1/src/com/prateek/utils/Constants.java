package com.prateek.utils;

public final class Constants {
	
	//error messages
	public static final String ERROR_USER_FETCH = "User could not be fetched from database";
	public static final String ERROR_EMP_FETCH = "Employee details could not be fetched from database";
	public static final String ERROR_EMP_UPDATE = "Employee could not be updated to database";
	public static final String ERROR_EMP_CREATE = "Employee could not be added to database";
	public static final String ERROR_EMP_DELETE = "Employee could not be deleted from database";
	public static final String ERROR_JSON_PARSE = "Failed to parse object to json string";
	public static final String ERROR_OBJECT_PARSE = "Failed to parse json string to object";
	public static final String ERROR_LOGIN = "Invalid login credentials. Please enter valid credentials";
	public static final String ERROR_CONNECTION = "Failed to establish contact with server. Please try again after some time!!";
	
	//validation messages
	public static final String VALID_REQUIRD ="This is a mandatory field!";
	public static final String VALID_CHARACTER ="The size should be minimum 5 and maximum 50 characters!!";
	public static final String VALID_NAME ="This field cannot be more than 100 characters in size!!";
	public static final String VALID_ADDRESS ="This field cannot be more than 500 characters in size!!";
	public static final String VALID_DATE ="Date should be in this format - dd/mm/yyyy only!!";
	
	//
	public static final String RESULT_MESSAGE = "No Employee records present";
	public static final String LOGOUT_MESSAGE = "Successfully Logged out!!";
	public static final String EMPLOYEE_REDIRECT = "redirect:/displayEmployee";
	public static final String LOGIN_PAGE_REDIRECT = "redirect:/";
	
	// Regular expression patterns
	public static final String PATTERN_DATE = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$";
	
	// REST url request/response type
	public static final String TYPE_JSON = "application/json; charset=utf-8";
	
	// REST web api url
	public static final String REST_URL = "http://localhost:8080/exercise-services/webapi";
	
	// Server response code
	public static final int RESPONSE_OK = 0;
	public static final int RESPONSE_SERVER_DOWN = 1;
	public static final int RESPONSE_FAIL = 2;
	
	//session and request params
	public static final String SESSION_RECORDS = "records";
	public static final String SESSION_USER_NAME = "userName";
	public static final String REQUEST_INDEX = "index";
	public static final String REQUEST_USER = "user";
	public static final String REQUEST_EMPLOYEE = "employee";
	public static final String REQUEST_ERROR_MSG = "errMessage";

	// JSP pages
	public static final String LOGIN_PAGE = "login";
	public static final String EDIT_PAGE = "edit-page";
	public static final String DISPLAY_PAGE = "display-page";
	public static final String CREATE_PAGE = "create-page";
	
	private Constants() {}
}
