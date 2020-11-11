package com.prateek.controller;

import static com.prateek.utils.Constants.PREFIX;
import static com.prateek.utils.Constants.SUFFIX;
import static com.prateek.utils.Constants.SESSION_LIST;
import static com.prateek.utils.Constants.ERROR_IMAGE_DELETE;
import static com.prateek.controller.FileUploadServlet.UPLOAD_PAGE;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prateek.dao.IImageDAO;
import com.prateek.entity.ImageFile;
import com.prateek.factory.SimpleFactory;

/**
 * Servlet implementation class ImageUpdateServlet
 */
@WebServlet(
		name = "UpdateImage",
		urlPatterns = {"/updateImage"})
public class ImageUpdateServlet extends HttpServlet {
	private IImageDAO imageDao;
	
	public static final String EDIT_PAGE = PREFIX + "image-edit" + SUFFIX;
	private static final long serialVersionUID = 1L;
	private static final String TAG = "FileUploadServlet: ";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher disp;
		String res = request.getParameter("submit");
		
		try {
			int index = Integer.parseInt(request.getParameter("index"));
			List<ImageFile> list = (List<ImageFile>) request.getSession().getAttribute(SESSION_LIST);
			ImageFile file = list.get(index);
			
			if(res.equalsIgnoreCase("Delete")) {
				deleteFile(file,list);
				disp = getServletContext().getRequestDispatcher(UPLOAD_PAGE);
				disp.forward(request, response);
			}else {
				// parameter to display image information on jsp
				request.setAttribute("index", index);
				
				disp = getServletContext().getRequestDispatcher(EDIT_PAGE);
				disp.forward(request, response);
			}
		}catch(ServletException | NumberFormatException | IOException ex) {
			System.err.println(TAG + ERROR_IMAGE_DELETE);
			System.err.println(TAG + ex.getMessage());
		}
	}
	
	private void deleteFile(ImageFile file, List<ImageFile> list) {
		
		if(imageDao == null) 
			imageDao = SimpleFactory.getImageDao();
		
		imageDao.deleteImageFile(file);
		list.remove(file);
	}

}
