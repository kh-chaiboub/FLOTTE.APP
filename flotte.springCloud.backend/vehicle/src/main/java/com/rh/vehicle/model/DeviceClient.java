package com.rh.vehicle.model;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@FeignClient(value = "DEVICE-SERVICE")
public interface DeviceClient {

    @RequestMapping( headers = "{authorization}",method = RequestMethod.GET, value = "/api/devices/devices/imei/{deviceId}")
    Device getDeviceByDeviceID(@RequestHeader("authorization") String authorization,@PathVariable("deviceId") String deviceId);

}
// ,url = "http://localhost:8282"