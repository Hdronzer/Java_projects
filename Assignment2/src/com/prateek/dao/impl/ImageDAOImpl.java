package com.prateek.dao.impl;

import static com.prateek.utils.Constants.IMAGE_QUERY;
import static com.prateek.utils.Constants.IMAGE_SIZE_QUERY;
import static com.prateek.utils.Constants.ERROR_IMAGE_FETCH;
import static com.prateek.utils.Constants.ERROR_IMAGE_SAVE;
import static com.prateek.utils.Constants.ERROR_IMAGE_DELETE;
import static com.prateek.utils.Constants.ERROR_IMAGE_UPDATE;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.prateek.dao.IImageDAO;
import com.prateek.entity.ImageFile;
import com.prateek.factory.SimpleFactory;

/*
 * class to get image and its details from db
 */
public class ImageDAOImpl implements IImageDAO {

	private SessionFactory factory;
	private Transaction trx = null;
	
	private static final String TAG = "ImageDAOImpl: ";

	public ImageDAOImpl() {
		factory = SimpleFactory.getFactory();
	}
	
	@Override
	public List<ImageFile> getImages() {

		List<ImageFile> res = null;
		try(Session session = factory.openSession()) {
			trx = session.beginTransaction();
            Query<ImageFile> query = session.createQuery(IMAGE_QUERY,ImageFile.class);
            res = query.getResultList();
	        trx.commit();
	      //  Blob blob = res.get
		}catch(Exception ex) {
			 System.err.println(TAG + ERROR_IMAGE_FETCH);
			 System.err.println(TAG + ex.getMessage());
		}
		return res;
	}

	@Override
	public void saveImage(ImageFile imageFile) {
		
		try(Session session = factory.openSession()) {
			trx = session.beginTransaction();
			session.save(imageFile);
	        trx.commit();
		}catch(Exception ex) {
			 if(trx != null) trx.rollback();
			 
			 System.err.println(TAG + ERROR_IMAGE_SAVE);
			 System.err.println(TAG + ex.getMessage());
		}
		
	}
	
	@Override
	public Double getTotalImageSize() {
		Double size = 0.0;
		
		try(Session session = factory.openSession()) {
			trx = session.beginTransaction();
			Query<Double> query = session.createQuery(IMAGE_SIZE_QUERY);
			size = query.getResultList().get(0);
	        trx.commit();
		}catch(Exception ex) {
			 System.err.println(TAG + ERROR_IMAGE_SAVE);
			 System.err.println(TAG + ex.getMessage());
		}
		return size;
	}
	
	@Override
	public void deleteImageFile(ImageFile imageFile) {
		
		try(Session session = factory.openSession()) {
			trx = session.beginTransaction();
            session.delete(imageFile);
	        trx.commit();
		}catch(Exception ex) {
			 if(trx != null) trx.rollback();
			 
			 System.err.println(TAG + ERROR_IMAGE_DELETE);
			 System.err.println(TAG + ex.getMessage());
		}
	}

	@Override
	public void updateImageFile(ImageFile imageFile) {
		try(Session session = factory.openSession()) {
			trx = session.beginTransaction();
            session.update(imageFile);
	        trx.commit();
		}catch(Exception ex) {
			 if(trx != null) trx.rollback();
			 
			 System.err.println(TAG + ERROR_IMAGE_UPDATE);
			 System.err.println(TAG + ex.getMessage());
		}
	}
}
