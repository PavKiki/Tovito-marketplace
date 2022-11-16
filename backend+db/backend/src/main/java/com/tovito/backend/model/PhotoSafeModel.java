package com.tovito.backend.model;

public class PhotoSafeModel {
    private Long photoId;
    private String type;
    private String path;
    private ProductSafeModel product;

    public PhotoSafeModel() {
    }

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ProductSafeModel getProduct() {
        return product;
    }

    public void setProduct(ProductSafeModel product) {
        this.product = product;
    }
}
