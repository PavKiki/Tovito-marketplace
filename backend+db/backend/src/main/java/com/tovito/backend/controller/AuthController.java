package com.tovito.backend.controller;

import com.tovito.backend.entity.UserEntity;
import com.tovito.backend.model.UserSignInModel;
import com.tovito.backend.model.UserSignUpModel;
import com.tovito.backend.security.TokenGenerator;
import com.tovito.backend.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    TokenGenerator tokenGenerator;

    @Autowired
    DaoAuthenticationProvider daoAuthenticationProvider;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserSignUpModel userSignUpModel) {
        UserEntity user = customUserDetailsService.createUser(userSignUpModel);
        //principal - всё, credential - пароль
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getEmail());
        Authentication authentication = UsernamePasswordAuthenticationToken.authenticated(userDetails, userSignUpModel.getPassword(), userDetails.getAuthorities());
        return ResponseEntity.ok(tokenGenerator.createToken(authentication));
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserSignInModel userSignInModel) {
        Authentication authentication = daoAuthenticationProvider.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(userSignInModel.getEmail(), userSignInModel.getPassword()));
        return ResponseEntity.ok(tokenGenerator.createToken(authentication));
    }
}
