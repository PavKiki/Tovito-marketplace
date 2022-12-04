package com.tovito.backend.model;

import com.tovito.backend.entity.UserEntity;
import com.tovito.backend.repository.RoleRepo;

import java.util.Arrays;

public class UserSignUpModel {
    private String name;
    private String email;
    private String password;

    public UserSignUpModel() {
    }

    public UserEntity toEntity(RoleRepo roleRepo) {
        UserEntity entity = new UserEntity();
        entity.setName(this.getName());
        entity.setEmail(this.getEmail());
        entity.setPassword(this.getPassword());
        entity.setBalance(0.0);
        entity.setFrozen_balance(0.0);
        entity.setRoles(Arrays.asList(roleRepo.findByName("ROLE_USER")));

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
