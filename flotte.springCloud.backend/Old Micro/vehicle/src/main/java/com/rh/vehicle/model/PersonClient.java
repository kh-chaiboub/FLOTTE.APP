package com.rh.vehicle.model;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "PERSON-SERVICE")
public interface PersonClient {
    @RequestMapping(method = RequestMethod.GET, value = "/api/person/person/mle/{mle}")
    Person findByPersonMle(@PathVariable("mle") int mle);
}
