package com.rh.vehicle.model;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(value = "APILOCAL")
public interface LastPositionClient {
//    @RequestMapping(method = RequestMethod.GET, value = "/api/last-positions/last-positions/deviceID/{devicesID}")
    @RequestMapping(method = RequestMethod.GET, value = "/lastposition/{devicesID}")

    LastPosition getLastPositionByDeviceID(@PathVariable("devicesID") String deviceId);

}