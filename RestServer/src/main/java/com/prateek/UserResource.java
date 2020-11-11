package com.prateek;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.prateek.dao.IUserDAO;
import com.prateek.entity.User;
import com.prateek.factory.SimpleFactory;

/*
 * user resource for login
 */
@Path("user")
public class UserResource {
	private IUserDAO userDao;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public User authenticateUser(User user) {
		List<User> list;
		
		if(userDao == null)
			userDao = SimpleFactory.getUserDao();
		
		list = userDao.getUser(user);
		if(list == null)
			return null;
		else if(list.isEmpty())
			return new User();
		
		return list.get(0);
	}
}
