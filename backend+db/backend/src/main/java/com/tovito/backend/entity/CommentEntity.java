package com.tovito.backend.entity;

import com.tovito.backend.model.CommentSafeModel;

import javax.persistence.*;

@Entity
@Table(name = "Comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;

    @Column(nullable = false)
    private Integer rating;

    @Column(nullable = false)
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private ProductEntity product_comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "from_user_id", referencedColumnName = "user_id")
    private UserEntity commentsByUser;

    public CommentEntity() {
    }

    public CommentSafeModel toSafeModel() {
        CommentSafeModel safeModel = new CommentSafeModel();

        safeModel.setCommentId(this.getComment_id());
        safeModel.setRating(this.getRating());
        safeModel.setText(this.getText());
        safeModel.setProductComment(this.getProduct_comment().toSafeModel());
        safeModel.setCommentByUser(this.getCommentsByUser().toSafeModel());

        return safeModel;
    }

    public UserEntity getCommentsByUser() {
        return commentsByUser;
    }

    public void setCommentsByUser(UserEntity commentsByUser) {
        this.commentsByUser = commentsByUser;
    }

    public Long getComment_id() {
        return comment_id;
    }

    public void setComment_id(Long comment_id) {
        this.comment_id = comment_id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ProductEntity getProduct_comment() {
        return product_comment;
    }

    public void setProduct_comment(ProductEntity product_comment) {
        this.product_comment = product_comment;
    }
}
