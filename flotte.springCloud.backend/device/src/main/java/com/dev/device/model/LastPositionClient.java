package com.dev.device.model;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;


@FeignClient(value = "POSITION-SERVICE")
public interface LastPositionClient {
    @RequestMapping(method = RequestMethod.GET, value = "/lastposition/{devicesID}")

    LastPosition getLastPositionByDeviceID(@PathVariable("devicesID") String deviceId);

}