package com.tovito.backend.controller;

import com.tovito.backend.entity.UserEntity;
import com.tovito.backend.exception.EmailAlreadyRegistered;
import com.tovito.backend.repository.UserRepo;
import com.tovito.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity register(@RequestBody UserEntity user) {
        try {
            userService.register(user);
            return ResponseEntity.ok("Пользователь успешно сохранен!");
        }
        catch (EmailAlreadyRegistered e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
            return  ResponseEntity.badRequest().body("Unknown error!");
        }
    }

//    @GetMapping
//    public ResponseEntity getUsers() {
//        try {
//            return ResponseEntity.ok("Server is working!");
//        }
//        catch (Exception e) {
//            return ResponseEntity.badRequest().body("Error occured!");
//        }
//    }
}
