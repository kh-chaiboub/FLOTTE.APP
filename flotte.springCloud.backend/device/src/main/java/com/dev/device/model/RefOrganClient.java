package com.dev.device.model;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@FeignClient(value = "REFORGAN-SERVICE")
public interface RefOrganClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api/ref-organs/ref-organs/{ref}")
    Optional<RefOrgan> findByRefOrganId(@PathVariable("ref") String ref);



}
