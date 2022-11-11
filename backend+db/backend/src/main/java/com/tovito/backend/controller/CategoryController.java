package com.tovito.backend.controller;

import com.tovito.backend.entity.CategoryEntity;
import com.tovito.backend.exception.CategoryAlreadyExists;
import com.tovito.backend.exception.CategoryNotFound;
import com.tovito.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/categories")
@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET, params = "title")
    public ResponseEntity getCategoryByName(@RequestParam String title) {
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

    @RequestMapping(method = RequestMethod.GET, params = "id")
    public ResponseEntity getCategoryById(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(categoryService.getCategoryById(id));
        }
        catch (CategoryNotFound e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Unknown error!");
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAllCategories() {
        try {
            return ResponseEntity.ok(categoryService.findAllCategories());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Unknown error!");
        }
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity createCategory(@RequestBody CategoryEntity category) {
        try {
            categoryService.createCategory(category);
            return ResponseEntity.ok("Категория \"" + category.getTitle() + "\" успешно создана!");
        }
        catch (CategoryAlreadyExists e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
//            return ResponseEntity.badRequest().body("Unknown error!");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
