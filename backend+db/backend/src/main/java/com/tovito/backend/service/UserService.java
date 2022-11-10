package com.tovito.backend.service;

import com.tovito.backend.entity.UserEntity;
import com.tovito.backend.exception.EmailAlreadyRegistered;
import com.tovito.backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public UserEntity register (UserEntity user) throws EmailAlreadyRegistered {
        if (userRepo.findByEmail(user.getEmail()) != null) {
            throw new EmailAlreadyRegistered("Пользователь с данной электронной почтой уже существует!");
        }
        return userRepo.save(user);
    }
}
