package com.prateek.controller;

import static com.prateek.utils.Constants.PREFIX;
import static com.prateek.utils.Constants.SUFFIX;
import static com.prateek.utils.Constants.SESSION_LIST;
import static com.prateek.utils.Constants.ERROR_LOGIN;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.prateek.dao.IImageDAO;
import com.prateek.dao.IUserDAO;
import com.prateek.entity.ImageFile;
import com.prateek.entity.User;
import com.prateek.factory.SimpleFactory;

/**
 * Servlet implementation class AuthenticationServlet
 */
@WebServlet(
		name = "Authentication",
		urlPatterns = {"/authentication"})
public class AuthenticationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TAG = "AuthenticationServlet: ";
	
	private IUserDAO userDao;
	private IImageDAO imageDao;
	
	private final String ERROR_PAGE = PREFIX + "login" + SUFFIX;
	private final String UPLOAD_PAGE = PREFIX + "image-upload" + SUFFIX;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher disp;
		User user = new User();
		user.setUserName(request.getParameter("uname"));
		user.setPassword(request.getParameter("pass"));
		
		try {
			if(!performValidation(request,user)) {
				disp = getServletContext().getRequestDispatcher(ERROR_PAGE);
		        disp.forward(request, response);
			}else
				performAuthentication(request,response,user);
		}catch (ServletException | IOException e) {
			System.err.println(TAG + "Operation could not be completed");
			System.err.println(TAG + e.getMessage());
		}	
	}
	
	private boolean performValidation(HttpServletRequest request, User user) {
		boolean res = true;
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
		Set<ConstraintViolation<User>> cvs = validator.validate(user);
		
		if(!cvs.isEmpty()) {
			cvs.forEach(cv -> {
				if(cv.getPropertyPath().toString().equals("userName"))
					request.setAttribute("errName", cv.getMessage());	
				else
					request.setAttribute("errPass", cv.getMessage());
			});
			res = false;
		}

		return res;
	}
	
	private void performAuthentication(HttpServletRequest request, HttpServletResponse response, User user)
			throws ServletException, IOException {
		RequestDispatcher disp;
		userDao = SimpleFactory.getUserDao();
		List<User> res = userDao.getUser(user);
		
		if(res == null || res.isEmpty()) {
			request.setAttribute("errMessage", ERROR_LOGIN);
			disp = getServletContext().getRequestDispatcher(ERROR_PAGE);
	        disp.forward(request, response);
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("userName", res.get(0).getUserName());
			session.setAttribute(SESSION_LIST, getImages());
			disp = getServletContext().getRequestDispatcher(UPLOAD_PAGE);
	        disp.forward(request, response);
		}

	}
	
	private List<ImageFile> getImages() {
		imageDao = SimpleFactory.getImageDao();
		return imageDao.getImages();
	}

}
