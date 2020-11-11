package com.prateek.model;

import static com.prateek.utils.Constants.VALID_REQUIRD;
import static com.prateek.utils.Constants.VALID_CHARACTER;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
 * User class to hold user data for authentication
 */
public class User {
	private int id;
	
	@NotNull(message=VALID_REQUIRD)
	@Size(min=5,max=50,message=VALID_CHARACTER)
	private String userName;
	
	@NotNull(message=VALID_REQUIRD)
	private String password;
	
	public User() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
