package com.prateek.utils;

public final class Constants {

	// query strings
	public static final String USER_QUERY = "FROM User u WHERE u.userName = :userName and u.password = :password";
	public static final String EMPLOYEE_QUERY = "FROM Employee";
	
	//error messages
	public static final String ERROR_USER_FETCH = "User could not be fetched from database";
	public static final String ERROR_EMPLOYEE_FETCH = "Employee details could not be fetched from database";
	public static final String ERROR_EMP_CREATE = "Employee could not be created to database";
	public static final String ERROR_EMP_UPDATE = "Employee could not be updated to database";
	public static final String ERROR_EMP_DELETE = "Employee could not be deleted from database";

	private Constants() {}
}
