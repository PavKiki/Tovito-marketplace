package com.tovito.backend.controller;

import com.tovito.backend.entity.UserEntity;
import com.tovito.backend.model.UserSignUpModel;
import com.tovito.backend.service.CustomUserDetailsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    CustomUserDetailsManager customUserDetailsService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserSignUpModel userSignUpModel) {
        UserEntity user = customUserDetailsService.createUser(userSignUpModel);

        Authentication authentication = UsernamePasswordAuthenticationToken.authenticated(user, user.getPassword(), Collections.EMPTY_LIST);
        return ResponseEntity.ok("Пользователь успешно создан!");
    }
}
