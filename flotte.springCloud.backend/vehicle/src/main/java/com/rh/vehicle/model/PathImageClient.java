package com.rh.vehicle.model;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@FeignClient(value = "UPLOADFILES-SERVICE")
public interface PathImageClient {
    @RequestMapping(method = RequestMethod.GET, value = "/getImage/{imageName:.+}",
            produces = {MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_GIF_VALUE,MediaType.IMAGE_PNG_VALUE}
    )
    byte[] getImageWithMediaType(@PathVariable(name = "imageName") String fileName) throws IOException;



}
