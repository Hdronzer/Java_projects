package com.prateek.controller;

import static com.prateek.utils.Constants.SESSION_LIST;
import static com.prateek.controller.ImageUpdateServlet.EDIT_PAGE;
import static com.prateek.controller.FileUploadServlet.UPLOAD_PAGE;
import static com.prateek.utils.Constants.ERROR_IMAGE_UPDATE;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.prateek.dao.IImageDAO;
import com.prateek.entity.ImageFile;
import com.prateek.factory.SimpleFactory;
import com.prateek.utils.Helper;

/**
 * Servlet implementation class EditImageServlet
 */
@WebServlet(
		name = "EditImage",
		urlPatterns = {"/editImage"})
@MultipartConfig(
		maxFileSize=1024*1024*5,
		maxRequestSize = 1024*1024*5)
public class EditImageServlet extends HttpServlet {
	private IImageDAO imageDao;
	
	private static final long serialVersionUID = 1L;
	private static final String TAG = "EditImageServlet: ";
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
		Part part;
        RequestDispatcher disp;
		try {
			int index = Integer.parseInt(request.getParameter("index"));
			List<ImageFile> list = (List<ImageFile>)request.getSession().getAttribute(SESSION_LIST);
			ImageFile file = list.get(index);
			
			part = request.getPart("fileToUpload");
			if(part.getSize() > 0) {
				Double size;

				if(!Helper.validateImage(request,part,imageDao)) {
					request.setAttribute("index", index);
					disp = getServletContext().getRequestDispatcher(EDIT_PAGE);
					disp.forward(request, response);
					return;
				}
				
				file.setImage(Helper.convertImageToBytes(part));
				size = part.getSize()/(1024d*1024);
		        size = Double.parseDouble(String.format("%.02f", size));
				file.setImageSize(size);
			}
			file.setImageName(request.getParameter("imaegName"));
			updateFile(file);
			
			disp = getServletContext().getRequestDispatcher(UPLOAD_PAGE);
			disp.forward(request, response);
		}catch(ServletException | IOException | NumberFormatException ex) {
			System.err.println(TAG + ERROR_IMAGE_UPDATE);
			System.err.println(TAG + ex.getMessage());
		}

	}
	
	private void updateFile(ImageFile file) {
		
		if(imageDao == null) 
			imageDao = SimpleFactory.getImageDao();
		
		imageDao.updateImageFile(file);
	}

}
