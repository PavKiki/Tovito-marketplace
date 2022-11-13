package com.tovito.backend.repository;

import com.tovito.backend.entity.PhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepo extends JpaRepository<PhotoEntity, Long> {
}
