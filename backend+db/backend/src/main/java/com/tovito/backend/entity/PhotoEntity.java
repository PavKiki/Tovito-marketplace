package com.tovito.backend.entity;

import javax.persistence.*;

@Entity
public class PhotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long photo_id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String path;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product_photo;

    public PhotoEntity() {
    }

    public Long getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(Long photo_id) {
        this.photo_id = photo_id;
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

    public ProductEntity getProduct() {
        return product_photo;
    }

    public void setProduct(ProductEntity product) {
        this.product_photo = product;
    }
}
