package com.prateek.factory;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.prateek.dao.IImageDAO;
import com.prateek.dao.IUserDAO;
import com.prateek.dao.impl.ImageDAOImpl;
import com.prateek.dao.impl.UserDAOImpl;
import com.prateek.entity.ImageFile;
import com.prateek.entity.User;

public class SimpleFactory {
	
	private static IUserDAO userDao;
	private static IImageDAO ImageDao;
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
	
	public static IImageDAO getImageDao() {
		if(ImageDao == null)  {
			synchronized (SimpleFactory.class) {
				if(ImageDao == null)
					ImageDao = new ImageDAOImpl();
			}
		}
		return ImageDao;
	}
	
	public static SessionFactory getFactory() {
		if(factory == null) {
			synchronized (SimpleFactory.class) {
				if(factory == null)
					factory = new Configuration()
							.addAnnotatedClass(User.class)
							.addAnnotatedClass(ImageFile.class)
							.configure()
							.buildSessionFactory();
			}
		}
		return factory;
	}
	
}
