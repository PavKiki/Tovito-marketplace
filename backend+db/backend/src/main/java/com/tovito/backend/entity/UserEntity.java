package com.tovito.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import com.tovito.backend.model.UserSafeModel;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Users")
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
//    @Column(nullable = false)
//    private String role;
    @Column(nullable = false)
    private String wallet_id;
    @Column(nullable = false)
    private Double balance;
    @Column(nullable = false)
    private Double frozen_balance;

    @OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL,CascadeType.PERSIST,CascadeType.MERGE }, orphanRemoval = true, mappedBy = "user")
    private Set<ProductEntity> products = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL,CascadeType.PERSIST,CascadeType.MERGE }, orphanRemoval = true, mappedBy = "commentsByUser")
    private Set<CommentEntity> writtenComments = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id")
            )
    private Collection<RoleEntity> roles;

    public UserEntity() {
    }

    public UserSafeModel toSafeModel() {
        UserSafeModel safeUser = new UserSafeModel();

        safeUser.setUserId(this.getUser_id());
        safeUser.setName(this.getName());
        safeUser.setEmail(this.getEmail());
        safeUser.setWalletId(this.wallet_id);
        safeUser.setBalance(this.getBalance());
        safeUser.setFrozenBalance(this.getFrozen_balance());

        return safeUser;
    }

    public void addProduct(ProductEntity product) {
        products.add(product);
    }

    public void addComment(CommentEntity comment) {
        writtenComments.add(comment);
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

    public Collection<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Collection<RoleEntity> roles) {
        this.roles = roles;
    }
}
