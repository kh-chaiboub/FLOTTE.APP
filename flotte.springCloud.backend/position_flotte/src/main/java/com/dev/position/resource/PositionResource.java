package com.dev.position.resource;

import com.dev.position.domain.Position;
import com.dev.position.service.PositionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/positions")
public class PositionResource {
    private final Logger log = LoggerFactory.getLogger(PositionResource.class);
    private static final String ENTITY_NAME = "position";

    private PositionService positionService;

    public PositionResource(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping("/positions/{devicesID}/{dateD}/{dateF}")
    @PreAuthorize("hasAuthority('USER')")
    public List<Position> AllPositionBetweenDateByDeviceID(@PathVariable String devicesID,
                                                           @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm") Date dateD,
                                                           @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm") Date dateF) {

        return positionService.findbyDeviceIdBetweenDate(devicesID,dateD,dateF);

    }



    @DeleteMapping("/positions/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deletePosition(@PathVariable String id) {
        log.info("REST request to delete Position : {}", id);
       PositionService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.GONE);
    }


}
