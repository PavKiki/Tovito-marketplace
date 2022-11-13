package com.tovito.backend.model;

import com.tovito.backend.entity.CommentEntity;
import com.tovito.backend.entity.ProductEntity;
import com.tovito.backend.entity.UserEntity;
import com.tovito.backend.exception.InvalidRating;
import com.tovito.backend.exception.ProductNotFound;
import com.tovito.backend.exception.UserNotFound;
import com.tovito.backend.repository.CommentRepo;
import com.tovito.backend.repository.ProductRepo;
import com.tovito.backend.repository.UserRepo;
import org.apache.catalina.User;

import java.util.Optional;

public class CommentModel {
    private Integer rating;

    private Long fromUserId;

    private Long productId;

    private String text;

    public CommentModel() {
    }

    public CommentEntity toEntity(UserRepo userRepo, ProductRepo productRepo, CommentRepo commentRepo) throws InvalidRating, UserNotFound, ProductNotFound {
//        if (commentRepo.findById()) должен быть запрос к БДшке, проверяющий, не писал ли пользователь уже комментарий к данному продукту
//          и еще проверку шоб сами себе не писали, а то знаем таких
        CommentEntity entity = new CommentEntity();
        if (rating < 0 || rating > 10) throw new InvalidRating("Некорректный рейтинг!");
        else entity.setRating(this.getRating());

        entity.setText(this.getText());

        Optional<UserEntity> user = userRepo.findById(fromUserId);
        if (!user.isPresent()) throw new UserNotFound("id пользователя, отправившего комментарий, не найден!");
        else {
            user.get().addComment(entity);
            entity.setWritten_comments(user.get());
        }

        Optional<ProductEntity> product = productRepo.findById(productId);
        if (!product.isPresent()) throw new ProductNotFound("id продукта не найден!");
        else {
            product.get().addComment(entity);
            entity.setProduct_comment(product.get());
        }

        return entity;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
