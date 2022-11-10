package com.tovito.backend.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<ProductEntity> products;

    public CategoryEntity() {
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public String getCategory_title() {
        return title;
    }

    public void setCategory_title(String category_title) {
        this.title = category_title;
    }

    public String getCategory_description() {
        return description;
    }

    public void setCategory_description(String category_description) {
        this.description = category_description;
    }
}
