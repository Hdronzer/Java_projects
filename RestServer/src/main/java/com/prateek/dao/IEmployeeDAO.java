package com.prateek.dao;

import java.util.List;

import com.prateek.entity.Employee;

public interface IEmployeeDAO {
	
	public List<Employee> getEmployeeDetails();
	public boolean updateEmployeeDetails(Employee emp);
	public boolean deleteEmployeeDetails(int id);
	public Employee createEmployeeDetails(Employee emp);
}
