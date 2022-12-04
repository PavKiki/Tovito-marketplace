package com.tovito.backend.service;

import com.tovito.backend.entity.UserEntity;
import com.tovito.backend.exception.EmailAlreadyRegistered;
import com.tovito.backend.exception.UserNotFound;
import com.tovito.backend.exception.WrongPassword;
import com.tovito.backend.model.UserSafeModel;
import com.tovito.backend.model.UserSignInModel;
import com.tovito.backend.model.UserSignUpModel;
import com.tovito.backend.repository.RoleRepo;
import com.tovito.backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    public UserEntity register (UserSignUpModel user) throws EmailAlreadyRegistered {
        if (userRepo.findByEmail(user.getEmail()) != null) {
            throw new EmailAlreadyRegistered("Пользователь с данной электронной почтой уже существует!");
        }
        return userRepo.save(user.toEntity(roleRepo));
    }

    public List<UserSafeModel> findAllUsers() {
        List<UserSafeModel> safeUsers = new ArrayList<>();
        for (UserEntity user: userRepo.findAll()) {
            safeUsers.add(user.toSafeModel());
        }
        return safeUsers;
    }

    public UserSafeModel findUserByEmail(String email) throws UserNotFound {
        UserEntity user = userRepo.findByEmail(email);
        if (user == null) throw new UserNotFound("Пользователь с почтой \"" + email + "\" не зарегистрирован!");
        return user.toSafeModel();
    }

    public UserSafeModel findUserById(Long id) throws UserNotFound {
        Optional<UserEntity> user = userRepo.findById(id);
        if (!user.isPresent()) throw new UserNotFound("Пользователя с id = \"" + id + "\" не существует!");
        return user.get().toSafeModel();
    }

    public UserSafeModel loginUser(UserSignInModel user) throws UserNotFound, WrongPassword {
        UserEntity entity = userRepo.findByEmail(user.getEmail());
        if (entity == null) throw new UserNotFound("Пользователя с данной электронной почтой не существует!");
        if (!entity.getPassword().equals(user.getPassword())) throw new WrongPassword("Пароли не совпадают!");
        return entity.toSafeModel();
    }
}
