package com.prateek.bl;

import static com.prateek.utils.Constants.RESPONSE_OK;
import static com.prateek.utils.Constants.RESPONSE_FAIL;
import static com.prateek.utils.Constants.RESPONSE_SERVER_DOWN;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.prateek.model.Employee;
import com.prateek.model.User;
import com.prateek.repository.IEmployeeRepository;
/*
 * class that contains the main business logic of application
 */
import com.prateek.repository.IUserRepository;

@Service
public class BussinessLogic {
	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private IEmployeeRepository empRepository;
	
	// method to authenticate user
	public int authenticateUser(User user) {
		int result = RESPONSE_OK;
		User resUser = userRepository.getUser(user);
		
		if(resUser == null)
			result = RESPONSE_SERVER_DOWN;
		else if(resUser.getUserName() == null)
			result = RESPONSE_FAIL;
		else
			user.setUserName(resUser.getUserName());
		
		return result;
	}
	
	public List<Employee> getEmployees() {
		return empRepository.getEmployeeDetails();
	}
	
	public boolean updateEmployee(Employee emp) {
		return empRepository.updateEmployeeDetails(emp);
	}

	public boolean deleteEmployee(int id) {
		return empRepository.deleteEmployeeDetails(id);
	}

	public boolean createEmployee(@Valid Employee emp) {
		return empRepository.insertEmployeeDetails(emp);
	}
	
}
