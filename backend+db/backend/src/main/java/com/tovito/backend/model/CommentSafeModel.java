package com.tovito.backend.model;

public class CommentSafeModel {
    private Long commentId;
    private Integer rating;
    private String text;
    private ProductSafeModel productComment;
    private UserSafeModel commentByUser;

    public CommentSafeModel() {
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
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

    public ProductSafeModel getProductComment() {
        return productComment;
    }

    public void setProductComment(ProductSafeModel productComment) {
        this.productComment = productComment;
    }

    public UserSafeModel getCommentByUser() {
        return commentByUser;
    }

    public void setCommentByUser(UserSafeModel commentByUser) {
        this.commentByUser = commentByUser;
    }
}
