package com.dev.position.model;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "DEVICE-SERVICE")
public interface DeviceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/devices/devices/deviceID/{deviceId}")
    Device getDeviceByDeviceID(@PathVariable("deviceId") String deviceId);

    @RequestMapping(method = RequestMethod.GET,
            value = "/api/devices/devices/imei/{imei}",
            consumes = "application/json",
            produces = "application/json")
    Device getDeviceByImie(@PathVariable("imei") String deviceId);

}
// ,url = "http://localhost:8282"