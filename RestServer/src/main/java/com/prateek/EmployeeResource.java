package com.prateek;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.prateek.dao.IEmployeeDAO;
import com.prateek.entity.Employee;
import com.prateek.factory.SimpleFactory;

/*
 * employee resource for providing employee details
 */
@Path("employee")
public class EmployeeResource {
	private IEmployeeDAO employeeDao;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Employee> getEmployees() {
		List<Employee> list = null;

		if(employeeDao == null)
			employeeDao = SimpleFactory.getEmployeeDao();

		list = employeeDao.getEmployeeDetails();
		return list;
	}
	
	@POST
	@Path("create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Employee createEmployee(Employee emp) {
		Employee employee = null;
		
		if(employeeDao == null)
			employeeDao = SimpleFactory.getEmployeeDao();
		
		employee = employeeDao.createEmployeeDetails(emp);
		
		return employee;
					
	}
	
	@POST
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response updateEmployee(Employee emp) {
		if(employeeDao == null)
			employeeDao = SimpleFactory.getEmployeeDao();

		if(employeeDao.updateEmployeeDetails(emp))
			return Response.ok().build();
			
		return Response.status(412).build();
					
	}
	
	@DELETE
	@Path("delete/{empId}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteEmployee(@PathParam("empId")int empId) {
		if(employeeDao == null)
			employeeDao = SimpleFactory.getEmployeeDao();

		if(employeeDao.deleteEmployeeDetails(empId))
			return Response.ok().build();
			
		return Response.status(412).build();
	}
}
