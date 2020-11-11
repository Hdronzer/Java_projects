package com.prateek.utils;

public final class Constants {

	// query strings
	public static final String USER_QUERY = "FROM User u WHERE u.userName = :userName and u.password = :password";
	public static final String FLIGHT_QUERY_FARE = "FROM FlightDetails f WHERE f.departure = :departure and f.arrival = :arrival"
					+ " and f.flightDate = :flightDate and f.flightClass like concat('%',:flightClass,'%') ORDER BY f.fare";
	public static final String FLIGHT_QUERY_DURATION = "FROM FlightDetails f WHERE f.departure = :departure and f.arrival = :arrival"
			+ " and f.flightDate = :flightDate and f.flightClass like concat('%',:flightClass,'%') ORDER BY f.fare,f.flightDuration";
	public static final String MAP_QUERY = "FROM MapTable";
	
	//error messages
	public static final String ERROR_USER_FETCH = "User could not be fetched from database";
	public static final String ERROR_FLIGHT_FETCH = "Flight records could not be fetched from database";
	public static final String ERROR_MAP_ROW_FETCH = "Map table rows could not be fetched from database";
	public static final String ERROR_MAP_ROW_SAVE = "Map table rows could not be save to database";
	public static final String ERROR_LOGIN = "Invalid login credentials. Please enter valid credentials";
	public static final String ERROR_FILE_READ = "Data read operation could not complete for file";
	public static final String ERROR_BATCH_SAVE = "Flight details could not be saved to database";
	
	//validation messages
	public static final String VALID_REQUIRD ="This is a mandatory field!";
	public static final String VALID_CHARACTER ="Only 3 character(A-Z) code is allowed!!";
	public static final String VALID_DATE ="Date should be in this format - dd-mm-yyyy only!!";
	public static final String VALID_PREFERENCE ="Preferecne should be 1(fare) or 2(fare and duration) only!!";
	public static final String VALID_FLIGHT_CLASS ="Class can be either E or B only!!";
	
	//
	public static final String RESULT_MESSAGE = "No Flight records present for given input";
	// Regular expression patterns
	public static final String PATTERN_DATE = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$";
	public static final String PATTERN_CHARACTER = "^[A-Z]{3}";
	public static final String PATTERN_CLASS= "^[E|B]{1}";
	
	// JSP pages
	public static final String LOGIN_PAGE = "login";
	public static final String SEARCH_PAGE = "search-page";
	public static final String DISPLAY_PAGE = "display-page";
	
	//Directory location for csv files
	public static final String PATH = "C:\\csv files";
	
	private Constants() {}
}
