package com.tovito.backend.model;

import com.tovito.backend.entity.CategoryEntity;
import com.tovito.backend.entity.ProductEntity;
import com.tovito.backend.entity.UserEntity;
import com.tovito.backend.exception.CategoryNotFound;
import com.tovito.backend.exception.UserNotFound;
import com.tovito.backend.repository.CategoryRepo;
import com.tovito.backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ProductModel {
    private String title;
    private String description;
    private Integer price;
    private Long categoryId;
    private Long userId;

    public ProductModel() {}

    public ProductEntity toEntity(UserRepo userRepo, CategoryRepo categoryRepo) throws UserNotFound, CategoryNotFound {
        ProductEntity entity = new ProductEntity();
        entity.setTitle(this.getTitle());
        entity.setDescription(this.getDescription());
        entity.setPrice(this.getPrice());

        Optional<UserEntity> user = userRepo.findById(userId);
        if (!user.isPresent()) throw new UserNotFound("Ошибка вставки продукта! Пользователь с таким id не найден!");
        else {
            user.get().addProduct(entity);
            entity.setUser(user.get());
        }

        Optional<CategoryEntity> category = categoryRepo.findById(categoryId);
        if (!category.isPresent()) throw new CategoryNotFound("Ошибка вставки продукта! Категории с таким id не существует!");
        else {
            category.get().addProduct(entity);
            entity.setCategory(category.get());
        }

        return entity;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
