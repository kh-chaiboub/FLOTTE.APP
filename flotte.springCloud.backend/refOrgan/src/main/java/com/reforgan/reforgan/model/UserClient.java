package com.reforgan.reforgan.model;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@FeignClient(value = "USER-WS")
public interface UserClient {
    @RequestMapping(method = RequestMethod.GET, value = "/users/users/id/{id}")
    Optional<User> findUserById(@PathVariable("id") String id);



}
