package com.prateek.dao.impl;

import static com.prateek.utils.Constants.USER_QUERY;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.prateek.dao.IUserDAO;
import com.prateek.entity.User;
import com.prateek.factory.SimpleFactory;

/*
 * class to get user from db
 */
public class UserDAOImpl implements IUserDAO {
	
	private SessionFactory factory;

	@Override
	public List<User> getUser(User user) {
		
		Transaction trx = null;
		List<User> res = null;
		factory = SimpleFactory.getFactory();
		try(Session session = factory.openSession()) {
			trx = session.beginTransaction();
            Query<User> query = session.createQuery(USER_QUERY,User.class);
            query.setParameter("userName", user.getUserName());
            query.setParameter("password", user.getPassword());
            res = query.getResultList();
	        trx.commit();
		}catch(Exception ex) {	            
			 System.err.println("Operation could not be completed");
			 System.err.println(ex.getMessage());
		}
		return res;
	}
	


}
