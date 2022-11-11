package com.tovito.backend.service;

import com.tovito.backend.entity.ProductEntity;
import com.tovito.backend.entity.UserEntity;
import com.tovito.backend.exception.UserNotFound;
import com.tovito.backend.repository.ProductRepo;
import com.tovito.backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    public ProductRepo productRepo;
    @Autowired
    public UserRepo userRepo;

    public ProductEntity createProduct(ProductEntity product) {
        return productRepo.save(product);
    }

    public Iterable<ProductEntity> getAllProducts() {
        return productRepo.findAll();
    }
}
