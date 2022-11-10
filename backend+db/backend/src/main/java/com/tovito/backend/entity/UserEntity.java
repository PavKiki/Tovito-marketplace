package com.tovito.backend.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String role;
    @Column(nullable = false)
    private String wallet_id;
    @Column(nullable = false)
    private Double balance;
    @Column(nullable = false)
    private Double frozen_balance;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<ProductEntity> products;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "written_comments")
    private List<CommentEntity> writtenComments;

    public UserEntity() {
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getWallet_id() {
        return wallet_id;
    }

    public void setWallet_id(String wallet_id) {
        this.wallet_id = wallet_id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getFrozen_balance() {
        return frozen_balance;
    }

    public void setFrozen_balance(Double frozen_balance) {
        this.frozen_balance = frozen_balance;
    }
}
