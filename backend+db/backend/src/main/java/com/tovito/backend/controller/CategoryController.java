package com.tovito.backend.controller;

import com.tovito.backend.exception.CategoryNotFound;
import com.tovito.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity getCategory(@RequestParam String title) {
        try {
            return ResponseEntity.ok(categoryService.getCategoryByName(title));
        }
        catch (CategoryNotFound e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Unknown error!");
        }
    }
}
