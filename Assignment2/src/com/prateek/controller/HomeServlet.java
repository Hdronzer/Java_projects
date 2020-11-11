package com.prateek.controller;

import static com.prateek.utils.Constants.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TAG = "HomeServlet: ";
	
	private final String PAGE = PREFIX + "login" + SUFFIX;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    	
		try {
			processRequest(request, response);
		}catch (ServletException | IOException e) {
			System.err.println(TAG + "Could not load page");
			System.err.println(TAG + e.getMessage());
		}
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			processRequest(request, response);
		}catch (ServletException | IOException e) {
			System.err.println(TAG + "Could not load page");
			System.err.println(TAG + e.getMessage());
		}
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(PAGE);
		
		dispatcher.forward(request, response);
	}

}
