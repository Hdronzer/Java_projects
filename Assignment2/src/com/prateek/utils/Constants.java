package com.prateek.utils;

public final class Constants {

	public static final String PREFIX = "/WEB-INF/view/";
	public static final String SUFFIX = ".jsp";
	// query strings
	public static final String USER_QUERY = " FROM User U WHERE U.userName = :userName and U.password = :password";
	public static final String IMAGE_QUERY = " FROM ImageFile";
	public static final String IMAGE_SIZE_QUERY = " SELECT sum(imageSize) FROM ImageFile";
	
	//error messages
	public static final String ERROR_LOGIN = "Invalid login credentials. Please enter valid credentials";
	public static final String ERROR_IMAGE_SIZE = "Image should be less than 1 mb in size";
	public static final String ERROR_TOTAL_IMAGE_SIZE = "Your total image size exceeds 10 mb. Hence cannot Uplaod it!!";
	public static final String ERROR_IMAGE_EMPTY = "Image cannot be empty!";
	public static final String ERROR_IMAGE_FETCH = "Image could not be fetched from db";
	public static final String ERROR_IMAGE_CAST = "Image could not converted to byte array";
	public static final String ERROR_IMAGE_SAVE = "Image could not be saved to data base";
	public static final String ERROR_IMAGE_DELETE = "Image could not be deleted from data base";
	public static final String ERROR_IMAGE_UPDATE = "Image could not be update to data base";
	
	// session attribute
	public static final String SESSION_LIST = "images";
	
	private Constants() {}
}
