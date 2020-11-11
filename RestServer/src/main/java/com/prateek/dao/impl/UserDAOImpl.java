package com.prateek.dao.impl;

import static com.prateek.utils.Constants.USER_QUERY;
import static com.prateek.utils.Constants.ERROR_USER_FETCH;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.prateek.dao.IUserDAO;
import com.prateek.entity.User;
import com.prateek.factory.SimpleFactory;

/*
 * DAO class to verify user credentials from db
 */
public class UserDAOImpl implements IUserDAO {
	private SessionFactory factory;

	private static final String TAG = "UserDAOImpl: ";
	
	public UserDAOImpl() {
		factory = SimpleFactory.getFactory();
	}
	
	@Override
	public List<User> getUser(User user) {
		Transaction trx = null;
		List<User> res = null;
		try(Session session = factory.openSession()) {
			trx = session.beginTransaction();
            Query<User> query = session.createQuery(USER_QUERY,User.class);
            query.setParameter("userName", user.getUserName());
            query.setParameter("password", user.getPassword());
            res = query.getResultList();
	        trx.commit();
		}catch(Exception ex) {	            
			 System.err.println(TAG + ERROR_USER_FETCH);
			 System.err.println(TAG + ex.getMessage());
		}
		return res;
	}
	


}
