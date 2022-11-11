package com.tovito.backend.service;

import com.tovito.backend.entity.CategoryEntity;
import com.tovito.backend.exception.CategoryAlreadyExists;
import com.tovito.backend.exception.CategoryNotFound;
import com.tovito.backend.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public CategoryEntity getCategoryById(Long category_id) throws CategoryNotFound {
        Optional<CategoryEntity> cat = categoryRepo.findById(category_id);
        if (!cat.isPresent()) {
            throw new CategoryNotFound("Категории с таким названием не существует!");
        }
        return cat.get();
    }

    public Iterable<CategoryEntity> findAllCategories() {
        return categoryRepo.findAll();
    }

    public CategoryEntity createCategory(CategoryEntity category) throws CategoryAlreadyExists {
        if (categoryRepo.findByTitle(category.getTitle()) != null) {
            throw new CategoryAlreadyExists("Категория с названием \"" + category.getTitle() + "\" уже существует!");
        }
        return categoryRepo.save(category);
    }
}
