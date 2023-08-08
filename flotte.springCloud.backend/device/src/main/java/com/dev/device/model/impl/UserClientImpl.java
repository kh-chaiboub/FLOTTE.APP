package com.dev.device.model.impl;

import com.dev.device.model.User;
import com.dev.device.model.UserClient;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
@Service
public class UserClientImpl implements  UserDetailsService {
    @Autowired
    UserClient userClient;

//    @Override
//    public Optional<User> findUserById(String id) {
//        return Optional.empty();
//    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
        System.out.println(userid);
        User user = userClient.findUserById(userid).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with -> username or email : " + userid));

        return (UserDetails) user;
    }
}
