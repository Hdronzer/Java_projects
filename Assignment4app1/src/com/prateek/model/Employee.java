package com.prateek.model;

import static com.prateek.utils.Constants.VALID_NAME;
import static com.prateek.utils.Constants.VALID_REQUIRD;

import java.io.Serializable;

import static com.prateek.utils.Constants.PATTERN_DATE;
import static com.prateek.utils.Constants.VALID_ADDRESS;
import static com.prateek.utils.Constants.VALID_DATE;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/*
 * Employee class to hold employee data for hr application
 */
public class Employee implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int empCode;
	
	@NotNull(message=VALID_REQUIRD)
	@Size(min=1,max=100,message=VALID_NAME)
	private String empName;
	
	@NotNull(message=VALID_REQUIRD)
	@Size(min=1,max=500,message=VALID_ADDRESS)
	private String address;
	
	@NotNull(message=VALID_REQUIRD)
	@Size(min=1,max=100,message=VALID_NAME)
	@Email()
	private String email;
	
	@NotNull(message=VALID_REQUIRD)
	@Pattern(regexp=PATTERN_DATE,message=VALID_DATE)
	private String birthDate;

	public Employee() {}
	
	public Employee(Employee emp) { // copy constructor
		this.empCode = emp.empCode;
		this.empName = emp.empName;
		this.address = emp.address;
		this.email = emp.email;
		this.birthDate = emp.birthDate;
	}
	
	public int getEmpCode() {
		return empCode;
	}

	public void setEmpCode(int empCode) {
		this.empCode = empCode;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	
}
