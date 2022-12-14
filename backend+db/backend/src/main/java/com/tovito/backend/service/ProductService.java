package com.tovito.backend.service;

import com.tovito.backend.entity.ProductEntity;
import com.tovito.backend.entity.UserEntity;
import com.tovito.backend.exception.CategoryNotFound;
import com.tovito.backend.exception.UserNotFound;
import com.tovito.backend.model.ProductModel;
import com.tovito.backend.model.ProductSafeModel;
import com.tovito.backend.repository.CategoryRepo;
import com.tovito.backend.repository.ProductRepo;
import com.tovito.backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    public ProductRepo productRepo;

    @Autowired
    public UserRepo userRepo;

    @Autowired
    public CategoryRepo categoryRepo;

    public ProductEntity createProduct(ProductModel product) throws UserNotFound, CategoryNotFound{
        ProductEntity entity = product.toEntity(userRepo, categoryRepo);
        return productRepo.save(entity);
    }

    public List<ProductSafeModel> getAllPhotosOfCategory(Long categoryId) {
        List<ProductSafeModel> safeModels = new ArrayList<>();
        for (ProductEntity product: productRepo.getAllProductsOfCategory(categoryId)) {
            safeModels.add(product.toSafeModel());
        }
        return safeModels;
    }

    public List<ProductSafeModel> getAllProducts() {
        List<ProductSafeModel> safeModels = new ArrayList<>();
        for (ProductEntity product: productRepo.findAll()) {
            safeModels.add(product.toSafeModel());
        }
        return safeModels;
    }
}
