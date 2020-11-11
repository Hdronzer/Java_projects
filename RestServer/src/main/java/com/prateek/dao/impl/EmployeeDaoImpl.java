package com.prateek.dao.impl;

import static com.prateek.utils.Constants.EMPLOYEE_QUERY;
import static com.prateek.utils.Constants.ERROR_EMPLOYEE_FETCH;
import static com.prateek.utils.Constants.ERROR_EMP_UPDATE;
import static com.prateek.utils.Constants.ERROR_EMP_CREATE;
import static com.prateek.utils.Constants.ERROR_EMP_DELETE;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.prateek.dao.IEmployeeDAO;
import com.prateek.entity.Employee;
import com.prateek.factory.SimpleFactory;

/*
 * DAO class to perform operation on employee details in db
 */
public class EmployeeDaoImpl implements IEmployeeDAO{
	private SessionFactory factory;

	private static final String TAG = "EmployeeDaoImpl: ";
	
	public EmployeeDaoImpl() {
		factory = SimpleFactory.getFactory();
	}
	
	@Override
	public List<Employee> getEmployeeDetails() {
		Transaction trx = null;
		List<Employee> res = null;
		try(Session session = factory.openSession()) {
			trx = session.beginTransaction();
            Query<Employee> query = session.createQuery(EMPLOYEE_QUERY,Employee.class);
            res = query.getResultList();
	        trx.commit();
		}catch(Exception ex) {	            
			 System.err.println(TAG + ERROR_EMPLOYEE_FETCH);
			 System.err.println(TAG + ex.getMessage());
		}
		return res;
	}

	@Override
	public boolean updateEmployeeDetails(Employee emp) {
		Transaction trx = null;
		boolean result = false;
		try(Session session = factory.openSession()) {
			trx = session.beginTransaction();
            session.update(emp);
	        trx.commit();
	        result = true;
		}catch(Exception ex) {	            
			 System.err.println(TAG + ERROR_EMP_UPDATE);
			 System.err.println(TAG + ex.getMessage());
			 if(trx != null) trx.rollback();
		}
		return result;
	}

	@Override
	public boolean deleteEmployeeDetails(int id) {
		Transaction trx = null;
		boolean result = false;
		try(Session session = factory.openSession()) {
			trx = session.beginTransaction();
            Employee emp = session.get(Employee.class, id);
            if(emp != null) {
                session.delete(emp);
    	        trx.commit();
    	        result = true;
            }
		}catch(Exception ex) {	            
			 System.err.println(TAG + ERROR_EMP_DELETE);
			 System.err.println(TAG + ex.getMessage());
			 if(trx != null) trx.rollback();
		}
		return result;
	}

	@Override
	public Employee createEmployeeDetails(Employee emp) {
		Transaction trx = null;
		Employee employee = null;
		try(Session session = factory.openSession()) {
			trx = session.beginTransaction();
            int id = (int) session.save(emp);
            employee = session.get(Employee.class, id);
	        trx.commit();
		}catch(Exception ex) {	            
			 System.err.println(TAG + ERROR_EMP_CREATE);
			 System.err.println(TAG + ex.getMessage());
			 if(trx != null) trx.rollback();
		}
		return employee;
	}

}
