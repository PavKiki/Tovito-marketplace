package com.tovito.backend.model;

import com.tovito.backend.entity.UserEntity;
import org.apache.catalina.User;

public class UserSignInModel {
    private String email;
    private String password;

    public UserSignInModel() {
    }

    public UserEntity toEntity() {
        UserEntity user = new UserEntity();
        user.setEmail(this.getEmail());
        return user;
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
