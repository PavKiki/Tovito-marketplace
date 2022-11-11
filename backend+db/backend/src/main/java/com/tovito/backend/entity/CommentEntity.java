package com.tovito.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "comment_entity")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;

    @Column(nullable = false)
    private Integer rating;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private ProductEntity product_comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "from_user_id", referencedColumnName = "user_id")
    private UserEntity written_comments;

    public CommentEntity() {
    }

    public UserEntity getWritten_comments() {
        return written_comments;
    }

    public void setWritten_comments(UserEntity written_comments) {
        this.written_comments = written_comments;
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

    public ProductEntity getProduct_comment() {
        return product_comment;
    }

    public void setProduct_comment(ProductEntity product_comment) {
        this.product_comment = product_comment;
    }
}
