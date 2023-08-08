package com.flotte.flotte.app.api.user.ui.service.imp;

import com.flotte.flotte.app.api.user.ui.data.UserEntity;
import com.flotte.flotte.app.api.user.ui.dto.UserDto;
import com.flotte.flotte.app.api.user.ui.repository.UserRepository;
import com.flotte.flotte.app.api.user.ui.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

    }


    @Override
    public UserDto createUsers(UserDto userDetails) {
        String usrId = UUID.randomUUID().toString();
        if(getByUserId(usrId) ==null){
            userDetails.setUserId(usrId);
        }
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);
            userEntity.setEncryptPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
            userRepository.save(userEntity);

            UserDto userDto = modelMapper.map(userEntity, UserDto.class);
            return userDto;



    }

    @Override
    public UserDto getUserDetailsByemail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) throw new UsernameNotFoundException(email);
        return new ModelMapper().map(userEntity, UserDto.class);
    }

    @Override
    public UserDto getUserDetailsByUserName(String userName) {
        UserEntity userEntity = userRepository.findByUsername(userName);
        if (userEntity == null) throw new UsernameNotFoundException(userName);
        return new ModelMapper().map(userEntity, UserDto.class);
    }

    @Override
    public UserDto getByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) throw new UsernameNotFoundException(userId);
        return new ModelMapper().map(userEntity, UserDto.class);
    }

    @Override
    public  List<UserEntity> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return users;
    }

    @Override
    public Optional<UserEntity> getById(String id) {

        Optional<UserEntity> userDto =   userRepository.findById(id);
         System.out.println(userDto);

        if (userDto == null) throw new UsernameNotFoundException(id);
        return userDto;
    }

    //    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        // UserEntity userEntity1 = userRepository.findByFirstname("daoaune4");
//        UserEntity userEntity = userRepository.findByEmail(email);
//
//        if (userEntity == null) throw new UsernameNotFoundException(email);
//
//        return new User(userEntity.getEmail(), userEntity.getEncryptPassword(), true, true, true, true, new ArrayList<>());
//    }
@Override
public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    // UserEntity userEntity1 = userRepository.findByFirstname("daoaune4");
    UserEntity userEntity = userRepository.findByUsername(userName);

    if (userEntity == null) throw new UsernameNotFoundException(userName);

    return new User(userEntity.getUsername(), userEntity.getEncryptPassword(), true, true, true, true, new ArrayList<>());
}
}
