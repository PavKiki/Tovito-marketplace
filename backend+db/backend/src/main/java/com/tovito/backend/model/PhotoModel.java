package com.tovito.backend.model;

import com.tovito.backend.entity.CommentEntity;
import com.tovito.backend.entity.PhotoEntity;
import com.tovito.backend.entity.ProductEntity;
import com.tovito.backend.exception.ProductNotFound;
import com.tovito.backend.repository.PhotoRepo;
import com.tovito.backend.repository.ProductRepo;

import java.util.Optional;

public class PhotoModel {
    private String path;
    private String type;
    private Long productId;

    public PhotoModel() {
    }

    public String getPath() {
        return path;
    }

    public PhotoEntity toEntity(PhotoRepo photoRepo, ProductRepo productRepo) throws ProductNotFound {
        PhotoEntity entity = new PhotoEntity();
        entity.setPath(this.getPath());
        entity.setType(this.getType());

        Optional<ProductEntity> product = productRepo.findById(productId);
        if (!product.isPresent()) throw new ProductNotFound("Невозможно добавить фото, т.к. товара не существует!");
        else {
            entity.setProduct_photo(product.get());
            product.get().addPhoto(entity);
        }
        return entity;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
