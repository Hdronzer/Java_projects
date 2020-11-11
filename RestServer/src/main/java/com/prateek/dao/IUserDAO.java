package com.prateek.dao;

import java.util.List;

import com.prateek.entity.User;

public interface IUserDAO {

	public List<User> getUser(User user);
}
