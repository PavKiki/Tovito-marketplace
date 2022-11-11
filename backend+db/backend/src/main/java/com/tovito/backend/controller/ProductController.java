package com.tovito.backend.controller;

import com.tovito.backend.entity.ProductEntity;
import com.tovito.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    public ProductService productService;

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity createProduct(@RequestBody ProductEntity product) {
        try {
            productService.createProduct(product);
            return ResponseEntity.ok("Продукт успешно добавлен!");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAllProducts() {
        try {
            return ResponseEntity.ok(productService.getAllProducts());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Unknown error!");
        }
    }
}
