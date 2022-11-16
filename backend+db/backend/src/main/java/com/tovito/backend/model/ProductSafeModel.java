package com.tovito.backend.model;

import com.tovito.backend.entity.CategoryEntity;

public class ProductSafeModel {
    private Long productId;
    private String title;
    private String description;
    private Integer price;
    private CategoryEntity category;
    private UserSafeModel user;

    public ProductSafeModel() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public UserSafeModel getUser() {
        return user;
    }

    public void setUser(UserSafeModel user) {
        this.user = user;
    }
}
