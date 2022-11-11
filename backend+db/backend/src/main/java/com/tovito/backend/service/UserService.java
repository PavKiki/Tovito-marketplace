package com.tovito.backend.service;

import com.tovito.backend.entity.UserEntity;
import com.tovito.backend.exception.EmailAlreadyRegistered;
import com.tovito.backend.exception.UserNotFound;
import com.tovito.backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Iterable<UserEntity> findAllUsers() {
        return userRepo.findAll();
    }

    public UserEntity findUserByEmail(String email) throws UserNotFound {
        UserEntity user = userRepo.findByEmail(email);
        if (user == null) throw new UserNotFound("Пользователь с почтой \"" + email + "\" не зарегистрирован!");
        return user;
    }

    public UserEntity findUserById(Long id) throws UserNotFound {
        Optional<UserEntity> user = userRepo.findById(id);
        if (!user.isPresent()) throw new UserNotFound("Пользователя с id = \"" + id + "\" не существует!");
        return user.get();
    }
}
