package com.rh.vehicle.model;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@FeignClient(value = "REFORGAN-SERVICE")
public interface RefOrganClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api/ref-organs/ref-organs/{ref}")
    RefOrgan findByRefOrganId(@PathVariable("ref") String ref);



}
