package com.flotte.flotte.app.api.user.ui.controlle;

import com.flotte.flotte.app.api.user.ui.data.UserEntity;
import com.flotte.flotte.app.api.user.ui.dto.UserDto;
import com.flotte.flotte.app.api.user.ui.model.CreateUserResponse;
import com.flotte.flotte.app.api.user.ui.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.flotte.flotte.app.api.user.ui.model.CreateUserRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UsersControlles {
    @Autowired
    private Environment env;

    @Autowired
    UserService userService;

    private UsersControlles(Environment env,UserService userService){
        this.env=env;
        this.userService=userService;
    }

    @GetMapping("/status/check")
    public String status() {

        return "users in port " + env.getProperty("local.server.port");
    }
    @GetMapping("/users/all")
    public List<UserEntity> AllUsers() {

        return userService.getAllUsers();
    }

    @GetMapping("/users/{userID}")
    public UserDto UserByUserId(@PathVariable String userID) {

        return userService.getByUserId(userID);
    }
    @GetMapping("/users/id/{id}")
    public Optional<UserEntity> UserById(@PathVariable String id) {

        return userService.getById(id);
    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@Valid @RequestBody CreateUserRequest userRequest) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = modelMapper.map(userRequest, UserDto.class);
        UserDto createUser = userService.createUsers(userDto);
        CreateUserResponse returnValue = modelMapper.map(createUser, CreateUserResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }
}
