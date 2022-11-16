package com.tovito.backend.service;

import com.tovito.backend.entity.CommentEntity;
import com.tovito.backend.exception.InvalidRating;
import com.tovito.backend.exception.ProductNotFound;
import com.tovito.backend.exception.UserNotFound;
import com.tovito.backend.model.CommentModel;
import com.tovito.backend.model.CommentSafeModel;
import com.tovito.backend.repository.CategoryRepo;
import com.tovito.backend.repository.CommentRepo;
import com.tovito.backend.repository.ProductRepo;
import com.tovito.backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    public ProductRepo productRepo;

    @Autowired
    public UserRepo userRepo;

    @Autowired
    public CommentRepo commentRepo;

    public CommentEntity createComment(CommentModel comment) throws InvalidRating, UserNotFound, ProductNotFound {
        CommentEntity entity = comment.toEntity(userRepo, productRepo, commentRepo);
        return commentRepo.save(entity);
    }

    public List<CommentSafeModel> getAllComments() {
        List<CommentSafeModel> safeModels = new ArrayList<>();
        for (CommentEntity comment: commentRepo.findAll()) {
            safeModels.add(comment.toSafeModel());
        }
        return safeModels;
    }
}
