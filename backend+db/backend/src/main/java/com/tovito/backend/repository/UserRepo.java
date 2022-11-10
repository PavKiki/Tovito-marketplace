package com.tovito.backend.repository;

import com.tovito.backend.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<UserEntity, Long>  {
    UserEntity findByEmail(String email);
}
