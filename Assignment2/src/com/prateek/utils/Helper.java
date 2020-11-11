package com.prateek.utils;

import static com.prateek.utils.Constants.ERROR_IMAGE_CAST;
import static com.prateek.utils.Constants.ERROR_IMAGE_EMPTY;
import static com.prateek.utils.Constants.ERROR_IMAGE_SIZE;
import static com.prateek.utils.Constants.ERROR_TOTAL_IMAGE_SIZE;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.prateek.dao.IImageDAO;
import com.prateek.factory.SimpleFactory;

public class Helper {

	private Helper() {}
	
	/* method to validate image size and other constraints */
	public static boolean validateImage(HttpServletRequest request, Part part, IImageDAO imageDao) {
		float size = 0;
		boolean result = true;
		String errMessage = "errMessage";
		
		if(part == null) {
			request.setAttribute(errMessage, ERROR_IMAGE_EMPTY);
			return false;
		}else 
			size = part.getSize()/(1024f*1024);
		if(size > 1) {
			request.setAttribute(errMessage, ERROR_IMAGE_SIZE);
			result = false;
		}
		if(imageDao == null)
			imageDao = SimpleFactory.getImageDao();
		
		if(imageDao.getTotalImageSize() > 10) {
			request.setAttribute(errMessage, ERROR_TOTAL_IMAGE_SIZE);
			result = false;
		}
		
		return result;
	}
	
	/* method to convert image to bytes for blob data */
    public static byte[] convertImageToBytes(Part part) 
    		throws IOException{
    	
    	byte[] bImage = new byte[(int)part.getSize()];
    	
		InputStream in = part.getInputStream();
    	in.read(bImage);
    	
    	return bImage;
    }
}
