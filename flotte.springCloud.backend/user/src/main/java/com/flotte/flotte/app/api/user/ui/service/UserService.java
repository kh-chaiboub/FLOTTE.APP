package com.flotte.flotte.app.api.user.ui.service;

import com.flotte.flotte.app.api.user.ui.data.UserEntity;
import com.flotte.flotte.app.api.user.ui.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    UserDto createUsers(UserDto userDetails);

    UserDto getUserDetailsByemail(String email);
    UserDto getUserDetailsByUserName(String userName);

    UserDto getByUserId(String userId);

    List<UserEntity> getAllUsers();

    Optional<UserEntity> getById(String id);


}
