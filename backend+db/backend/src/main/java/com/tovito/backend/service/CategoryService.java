package com.tovito.backend.service;

import com.tovito.backend.entity.CategoryEntity;
import com.tovito.backend.exception.CategoryNotFound;
import com.tovito.backend.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    public CategoryEntity getCategoryByName(String category_title) throws CategoryNotFound {
        CategoryEntity cat = categoryRepo.findByTitle(category_title);
        if (cat == null) {
            throw new CategoryNotFound("Категории с таким названием не существует!");
        }
        return cat;
    }
}
