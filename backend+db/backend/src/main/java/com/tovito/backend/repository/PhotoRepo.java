package com.tovito.backend.repository;

import com.tovito.backend.entity.PhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepo extends JpaRepository<PhotoEntity, Long> {
    @Query("select e from PhotoEntity e where e.product_photo.product_id = :productId")
    List<PhotoEntity> findPhotosOfProduct(@Param("productId") Long productId);
}
