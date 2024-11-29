package com.scm.services;

import com.scm.entity.UserEntity;
import com.scm.exceptions.ResourceNotFoundException;
import com.scm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface{

    private final UserRepository userRepository;



    @Override
    public UserEntity saveUser(UserEntity userEntity) {
//        generate userID before creating user
        String userId = UUID.randomUUID().toString();
        userEntity.setUserId(userId);

        return userRepository.save(userEntity);
    }

    @Override
    public Optional<UserEntity> getUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<UserEntity> updateUser(UserEntity userEntity) {
        UserEntity userEntity1 = userRepository.findById(userEntity.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        userEntity1.setName(userEntity.getName());
        userEntity1.setEmail(userEntity.getEmail());
        userEntity1.setPassword(userEntity.getPassword());
        userEntity1.setAbout(userEntity1.getAbout());
        userEntity1.setPhoneNumber(userEntity.getPhoneNumber());
        userEntity1.setProfilePic(userEntity.getProfilePic());
        userEntity1.setEnabled(userEntity.isEnabled());
        userEntity1.setEmailVerified(userEntity.isEmailVerified());
        userEntity1.setPhoneVerified(userEntity.isPhoneVerified());
        userEntity1.setProvider(userEntity.getProvider());
        userEntity1.setProviderUserId(userEntity.getProviderUserId());
        UserEntity saveUser = userRepository.save(userEntity1);
        return Optional.ofNullable(saveUser);
    }

    @Override
    public void deleteUser(String id) {
        UserEntity userEntity1 = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        userRepository.delete(userEntity1);
    }

    @Override
    public boolean isUserExist(String userId) {
        UserEntity userEntity1 = userRepository.findById(userId).orElse(null);
        return userEntity1!=null ? true:false;
    }

    @Override
    public boolean isUSerExistByEmail(String email) {
        UserEntity userEntity1 = userRepository.findByemail(email).orElse(null);
        return userEntity1!=null ? true:false;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}
