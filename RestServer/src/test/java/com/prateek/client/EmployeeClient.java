package com.prateek.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.prateek.entity.Employee;
import com.prateek.factory.SimpleFactory;

/*
 * Client class to call rest API for employee
 */
public class EmployeeClient {
	private Client client;
	private WebTarget target;
	private Employee emp;
	
	public EmployeeClient() {
		client = ClientBuilder.newClient();
		target = client.target("http://localhost:8080/exercise-services/webapi/employee/");
	}
	
	public Employee createEmployee(Employee emp) {
		Response response = target.path("create/")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(emp, MediaType.APPLICATION_JSON));

		if(response.getStatus() == 204)
			return null;
		
		if(response.getStatus() != 200)
			throw new RuntimeException(response.getStatus() + ": there was error on the server");

		return response.readEntity(Employee.class);
	}
	
	public boolean deleteEmployee(int empId) {
		boolean result = true;
		Response response = target.path("delete/"+empId).request(MediaType.TEXT_PLAIN).delete();
		if(response.getStatus() != 200)
			result = false;

		return result;
	}
	
	public boolean updateEmployee(Employee emp) {
		boolean result = true;
		Response response = target.path("update/")
				.request(MediaType.TEXT_PLAIN)
				.post(Entity.entity(emp, MediaType.APPLICATION_JSON));
		
		if(response.getStatus() != 200)
			result = false;

		return result;
	}
	
	public List<Employee> getEmployeeList() {
		List<Employee> list = null;
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		
		if(response.getStatus() == 200) {
			list = response.readEntity(new GenericType<List<Employee>>(){});
		}

		return list;
	}
	
	public Employee getDummyEmployee() {
		if(emp == null) {
			emp = SimpleFactory.getEmployee();
			emp.setEmpName("TestName");
			emp.setEmail("test@gmail.com");
			emp.setAddress("Test city");
			emp.setBirthDate("17/10/1990");
		}
		
		return emp;
	}
}
