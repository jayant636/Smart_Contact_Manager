package com.scm.services;

import com.scm.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserServiceInterface {

    UserEntity saveUser(UserEntity userEntity);
    Optional<UserEntity> getUserById(String id);
    Optional<UserEntity> updateUser(UserEntity userEntity);
    void deleteUser(String id);
    boolean isUserExist(String userId);
    boolean isUSerExistByEmail(String email);
    List<UserEntity> getAllUsers();
}
