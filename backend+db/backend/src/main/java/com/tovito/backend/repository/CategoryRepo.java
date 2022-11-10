package com.tovito.backend.repository;

import com.tovito.backend.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends CrudRepository<CategoryEntity, Long> {
    CategoryEntity findByTitle(String category_title);
}
