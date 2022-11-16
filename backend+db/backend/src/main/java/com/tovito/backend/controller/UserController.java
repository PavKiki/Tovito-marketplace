package com.tovito.backend.controller;

import com.tovito.backend.entity.UserEntity;
import com.tovito.backend.exception.EmailAlreadyRegistered;
import com.tovito.backend.exception.UserNotFound;
import com.tovito.backend.exception.WrongPassword;
import com.tovito.backend.model.UserSignInModel;
import com.tovito.backend.model.UserSignUpModel;
import com.tovito.backend.repository.UserRepo;
import com.tovito.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.config.RepositoryConfigurationSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody UserSignUpModel user) {
        try {
            userService.register(user.toEntity());
            return ResponseEntity.ok("Пользователь успешно сохранен!");
        }
        catch (EmailAlreadyRegistered e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
            return  ResponseEntity.badRequest().body("Unknown error!");
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAllUsers() {
        try {
            return ResponseEntity.ok(userService.findAllUsers());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Unknown error!");
        }
    }

    @RequestMapping(method = RequestMethod.GET, params = "email")
    public ResponseEntity getUserByEmail(@RequestParam String email) {
        try {
            return ResponseEntity.ok(userService.findUserByEmail(email));
        }
        catch (UserNotFound e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Unknown error!");
        }
    }

    @RequestMapping(method = RequestMethod.GET, params = "id")
    public ResponseEntity getUserById(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(userService.findUserById(id));
        }
        catch (UserNotFound e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Unknown error!");
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity loginUser(@RequestBody UserSignInModel userData) {
        try {
            userService.loginUser(userData);
            return ResponseEntity.ok("Пользователь успешно вошел в систему!");
        }
        catch (UserNotFound e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (WrongPassword e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
