package com.tovito.backend.service;

import com.tovito.backend.entity.PhotoEntity;
import com.tovito.backend.entity.ProductEntity;
import com.tovito.backend.exception.CategoryNotFound;
import com.tovito.backend.exception.InvalidPhotoType;
import com.tovito.backend.exception.ProductNotFound;
import com.tovito.backend.exception.UserNotFound;
import com.tovito.backend.model.PhotoModel;
import com.tovito.backend.model.ProductModel;
import com.tovito.backend.repository.PhotoRepo;
import com.tovito.backend.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {
    @Autowired
    public PhotoRepo photoRepo;
    @Autowired
    public ProductRepo productRepo;

    public PhotoEntity createPhoto(PhotoModel photo) throws ProductNotFound, InvalidPhotoType {
        PhotoEntity entity = photo.toEntity(photoRepo, productRepo);
        if (!entity.getType().equals("jpeg") && !entity.getType().equals("jpg") && !entity.getType().equals("png")) {
            throw new InvalidPhotoType("Некорректное расширение изображения!");
        }
        return photoRepo.save(entity);
    }

    public List<PhotoEntity> getPhotosOfProduct(Long productId) {
        return photoRepo.findPhotosOfProduct(productId);
    }

    public Iterable<PhotoEntity> getAllPhotos() {
        return photoRepo.findAll();
    }
}
