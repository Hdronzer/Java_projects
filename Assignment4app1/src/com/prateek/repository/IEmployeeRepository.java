package com.prateek.repository;

import java.util.List;

import com.prateek.model.Employee;

public interface IEmployeeRepository {
	
	public List<Employee> getEmployeeDetails();
	public boolean updateEmployeeDetails(Employee emp);
	public boolean deleteEmployeeDetails(int id);
	public boolean insertEmployeeDetails(Employee emp);
}
