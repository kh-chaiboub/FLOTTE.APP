package com.dev.person.model;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "REFORGAN-SERVICE")
public interface RefOrganClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api/ref-organs/ref-organs/{id}")
    RefOrgan getRefOrgan(@RequestParam("id") String id);



}