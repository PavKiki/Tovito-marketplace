package com.tovito.backend.repository;

import com.tovito.backend.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends JpaRepository<CommentEntity, Long> {
}
