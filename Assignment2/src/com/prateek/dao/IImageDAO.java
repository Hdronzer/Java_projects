package com.prateek.dao;

import java.util.List;

import com.prateek.entity.ImageFile;

public interface IImageDAO {
	
	public List<ImageFile> getImages();
	
	public void saveImage(ImageFile imageFile);

	public Double getTotalImageSize();

	public void deleteImageFile(ImageFile imageFile);

	public void updateImageFile(ImageFile imageFile);
}
