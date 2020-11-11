package com.prateek.factory;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.prateek.dao.IEmployeeDAO;
import com.prateek.dao.IUserDAO;
import com.prateek.dao.impl.EmployeeDaoImpl;
import com.prateek.dao.impl.UserDAOImpl;
import com.prateek.entity.Employee;
import com.prateek.entity.User;

public class SimpleFactory {
	
	private static IUserDAO userDao;
	private static IEmployeeDAO employeeDao;
	private static SessionFactory factory;
	
	private SimpleFactory() {}
	
	public static IUserDAO getUserDao() {
		if(userDao == null)  {
			synchronized (SimpleFactory.class) {
				if(userDao == null)
					userDao = new UserDAOImpl();
			}
		}
		return userDao;
	}
	
	public static IEmployeeDAO getEmployeeDao() {
		if(employeeDao == null)  {
			synchronized (SimpleFactory.class) {
				if(employeeDao == null)
					employeeDao = new EmployeeDaoImpl();
			}
		}
		return employeeDao;
	}
	
	public static SessionFactory getFactory() {
		if(factory == null) {
			synchronized (SimpleFactory.class) {
				if(factory == null)
					factory = new Configuration()
							.addAnnotatedClass(User.class)
							.addAnnotatedClass(Employee.class)
							.configure()
							.buildSessionFactory();
			}
		}
		return factory;
	}
	
	public static Employee getEmployee() {
		return new Employee(); 
	}
	
}
