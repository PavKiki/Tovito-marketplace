package com.tovito.backend.repository;

import com.tovito.backend.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity, Long> {
    @Query("select e from ProductEntity e where e.category.category_id = :categoryId")
    List<ProductEntity> getAllProductsOfCategory(@Param("categoryId") Long categoryId);
}
