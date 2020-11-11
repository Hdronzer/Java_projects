package com.prateek.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.prateek.client.EmployeeClient;
import com.prateek.entity.Employee;

public class EmployeeClientTest {
	private Employee emp;
	
	@Test
	public void testCreateEmployeeFunctionality() {
		EmployeeClient client = new EmployeeClient();
		emp = client.getDummyEmployee();
		
		try {
			emp = client.createEmployee(emp);
			assertNotNull("Error in functionality of createEmployee() API: Employee could not be created", emp);
		}catch (RuntimeException e) {
			System.out.println(e);
			assertTrue("Fail in testcase testCreateEmployeeFunctionality:" + e.getMessage(), false);
		}finally {
			boolean result = client.deleteEmployee(emp.getEmpCode());
			assertTrue("Fail in testcase testCreateEmployeeFunctionality: Employee could not be deleted", result);
		}
	}
	
	@Test
	public void testDeleteEmployeeFunctionality() {
		EmployeeClient client = new EmployeeClient();
		emp = client.getDummyEmployee();
		
		try {
			emp = client.createEmployee(emp);
			assertNotNull("Fail in testcase testDeleteEmployeeFunctionality: Employee could not be created ", emp);
			
			boolean result = client.deleteEmployee(emp.getEmpCode());
			assertTrue("Error in functionality of deleteEmployee() API: Failed to delete employee",
					result);
			
			result = client.deleteEmployee(emp.getEmpCode());
			assertFalse("Error in functionality of deleteEmployee() API: Non existing element deleted",
					result);
		}catch (RuntimeException e) {
			System.out.println(e);
			assertTrue("Fail in testcase testDeleteEmployeeFunctionality:" + e.getMessage(), false);
		}
	}
	
	@Test
	public void testUpdateEmployeeFunctionality() {
		boolean result = false;
		EmployeeClient client = new EmployeeClient();
		emp = client.getDummyEmployee();
		
		try {
			emp = client.createEmployee(emp);
			assertNotNull("Fail in testcase testUpdateEmployeeFunctionality: Employee could not be created ", emp);
			
			emp.setEmpName("Testing");
			result = client.updateEmployee(emp);
			assertTrue("Error in functionality of updateEmployee() API: Employee could not be updated", result);

		}catch (RuntimeException e) {
			System.out.println(e);
			assertTrue("Fail in testcase testDeleteEmployeeFunctionality:" + e.getMessage(), false);
		}finally {
			result = client.deleteEmployee(emp.getEmpCode());
			assertTrue("Fail in testcase testUpdateEmployeeFunctionality: Employee could not be deleted", result);
		}
	}
	
	@Test
	public void testGetEmployeesFunctionality() {
		EmployeeClient client = new EmployeeClient();
		emp = client.getDummyEmployee();
		
		try {
			emp = client.createEmployee(emp);
			assertNotNull("Fail in testcase testGetEmployeesFunctionality: Employee could not be created ", emp);
			
			List<Employee> empList = client.getEmployeeList();
			assertNotNull("Error in functionality of getEmployees() API: List returned is null",
					empList);
			
			assertTrue("Error in functionality of getEmployees() API: List returned is not correct",
					empList.stream()
					.filter(e -> e.getEmpCode() == emp.getEmpCode())
					.count() == 1);
		}catch (RuntimeException e) {
			System.out.println(e);
			assertTrue("Fail in testcase testGetEmployeesFunctionality:" + e.getMessage(), false);
		}finally {
			boolean result = client.deleteEmployee(emp.getEmpCode());
			assertTrue("Fail in testcase testGetEmployeesFunctionality: Employee could not be deleted", result);
		}
	}

}
