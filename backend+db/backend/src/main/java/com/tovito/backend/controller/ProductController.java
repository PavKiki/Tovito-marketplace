package com.tovito.backend.controller;

import com.tovito.backend.entity.ProductEntity;
import com.tovito.backend.exception.CategoryNotFound;
import com.tovito.backend.exception.UserNotFound;
import com.tovito.backend.model.ProductModel;
import com.tovito.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    public ProductService productService;

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity createProduct(@RequestBody ProductModel product) {
        try {
            productService.createProduct(product);
            return ResponseEntity.ok("Продукт успешно добавлен!");
        }
        catch (UserNotFound e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (CategoryNotFound e) {
            return ResponseEntity.badRequest().body(e.getMessage());
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

    @RequestMapping(value = "/ofcategory", params = "id", method = RequestMethod.GET)
    public ResponseEntity getAllProductsOfCategory(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(productService.getAllPhotosOfCategory(id));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
