package com.rh.vehicle.model;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpHeaders;
import java.util.Map;

@FeignClient(value = "DEVICE-SERVICE")
public interface DeviceClient {

    @RequestMapping( headers = "{authorization}",method = RequestMethod.GET, value = "/api/devices/devices/imei/{deviceId}")
    Device getDeviceByDeviceID(@RequestHeader("authorization") String authorization,@PathVariable("deviceId") String deviceId);

}
// ,url = "http://localhost:8282"