package com.tovito.backend.model;

public class UserSignInModel {
    private String email;
    private String password;

    public UserSignInModel() {
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
