package com.flotte.flotte.app.api.user.ui.repository;

import com.flotte.flotte.app.api.user.ui.data.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String userName);
    UserEntity findByUserId(String userID);

    Optional<UserEntity> findById(String id);
}
