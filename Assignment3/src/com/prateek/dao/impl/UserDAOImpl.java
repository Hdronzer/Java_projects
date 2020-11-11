package com.prateek.dao.impl;

import static com.prateek.utils.Constants.USER_QUERY;
import static com.prateek.utils.Constants.ERROR_USER_FETCH;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.prateek.dao.IUserDAO;
import com.prateek.entity.User;

/*
 * DAO class to verify user credentials from db
 */
@Repository
public class UserDAOImpl implements IUserDAO {
	
	@Autowired
	private SessionFactory factory;

	private static final String TAG = "UserDAOImpl: ";
	
	@Override
	@Transactional
	public List<User> getUser(User user) {
		
		List<User> res = null;
		try(Session session = factory.openSession()) {
            Query<User> query = session.createQuery(USER_QUERY,User.class);
            query.setParameter("userName", user.getUserName());
            query.setParameter("password", user.getPassword());
            res = query.getResultList();
	        
		}catch(Exception ex) {	            
			 System.err.println(TAG + ERROR_USER_FETCH);
			 System.err.println(TAG + ex.getMessage());
		}
		return res;
	}
	


}
