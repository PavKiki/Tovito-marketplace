package com.tovito.backend.model;

import com.tovito.backend.entity.UserEntity;

public class UserSignUpModel {
    private String name;
    private String email;
    private String password;

    public UserSignUpModel() {
    }

    public UserEntity toEntity() {
        UserEntity entity = new UserEntity();
        entity.setName(this.getName());
        entity.setEmail(this.getEmail());
        entity.setPassword(this.getPassword());
        entity.setBalance(0.0);
        entity.setFrozen_balance(0.0);

        //Further realization is needed
        entity.setRole("customer");
        //Connect with EOSIO
        entity.setWallet_id("puk-srenk");

        return entity;
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
}
