package com.prateek.controller;

import static com.prateek.utils.Constants.PREFIX;
import static com.prateek.utils.Constants.SUFFIX;
import static com.prateek.utils.Constants.SESSION_LIST;
import static com.prateek.utils.Constants.ERROR_IMAGE_EMPTY;
import static com.prateek.utils.Constants.ERROR_IMAGE_CAST;
import static com.prateek.utils.Constants.ERROR_IMAGE_SIZE;
import static com.prateek.utils.Constants.ERROR_TOTAL_IMAGE_SIZE;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.prateek.dao.IImageDAO;
import com.prateek.entity.ImageFile;
import com.prateek.factory.SimpleFactory;
import com.prateek.utils.Helper;

/**
 * Servlet implementation class FileUploadServlet
 */
@WebServlet(
		name = "UploadFile",
		urlPatterns = {"/uploadFile"})
@MultipartConfig(
		maxFileSize=1024*1024*5,
		maxRequestSize = 1024*1024*5)
public class FileUploadServlet extends HttpServlet {
	private IImageDAO imageDao;
	
	
	public static final String UPLOAD_PAGE = PREFIX + "image-upload" + SUFFIX;
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
		Part part;
        RequestDispatcher disp;
		try {
			part = request.getPart("fileToUpload");
			if(Helper.validateImage(request,part,imageDao))
				saveFile(request,part);
			
			disp = getServletContext().getRequestDispatcher(UPLOAD_PAGE);
			disp.forward(request, response);
		}catch(ServletException | IOException ex) {
			ex.printStackTrace();
		}
	}
	
	private void saveFile(HttpServletRequest request, Part part) {
		ImageFile image = new ImageFile();
		imageDao = SimpleFactory.getImageDao();
		HttpSession session = request.getSession();
		List<ImageFile> list = (List<ImageFile>) session.getAttribute(SESSION_LIST);
		Double size;
		
        image.setImageName(extractFileName(part));
        size = part.getSize()/(1024d*1024);
        size = Double.parseDouble(String.format("%.02f", size));
        image.setImageSize(size);
        
        try {
        	image.setImage(Helper.convertImageToBytes(part));
        }catch(IOException ex) {
    		System.err.println(TAG + ERROR_IMAGE_CAST);
			System.err.println(TAG + ex.getMessage());
			return;
        }
        
        imageDao.saveImage(image);
        list.add(image);
        session.setAttribute(SESSION_LIST, list);
	}
	
	/* method to read file name */ 
    private String extractFileName(Part part) {
        String fileName [] = null;
        String contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                fileName = item.substring(item.indexOf("=") + 2, item.length() - 1)
                		.split(Pattern.quote(System.getProperty("file.separator")));
                fileName = fileName[fileName.length-1].split("\\.");
            }
        }
        return fileName[0];
    }
}
