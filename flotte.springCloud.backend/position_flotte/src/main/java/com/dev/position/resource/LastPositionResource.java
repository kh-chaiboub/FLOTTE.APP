package com.dev.position.resource;

import com.dev.position.domain.LastPosition;
import com.dev.position.service.LastPositionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/last-positions")
public class LastPositionResource {
    private final Logger log = LoggerFactory.getLogger(LastPositionResource.class);
    private static final String ENTITY_NAME = "lastPosition";

//    private LastPositionService lastPositionService;


    private LastPositionService lastPositionService;

    public LastPositionResource(LastPositionService lastPositionService) {
        this.lastPositionService = lastPositionService;
    }

    @GetMapping("/last-positions")
    @PreAuthorize("hasAuthority('USER')")
    public List<LastPosition> AllLastPosition() {
        return lastPositionService.findAll();

    }

    @GetMapping("/last-positions/{positionId}")
    @PreAuthorize("hasAuthority('USER')")
    public Optional<LastPosition> getLastPositionById(@PathVariable String positionId) {
        log.info("REST request to get LastPosition : {}", positionId);
        Optional<LastPosition> lastPosition = lastPositionService.getLastPositionById(positionId);

        return lastPosition;

    }

    @GetMapping("/last-positions/deviceID/{devicesID}")
    @PreAuthorize("hasAuthority('USER')")
    public Optional<LastPosition> getLastPositionByDeviceId(@PathVariable String devicesID) {
        log.info("REST request to get LastPosition by devicesID : {}", devicesID);
        Optional<LastPosition> lastPosition = lastPositionService.getLastPositionByDeviceId(devicesID);

        return lastPosition;

    }


    @DeleteMapping("/last-positions/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteLastPosition(@PathVariable String id) {
        log.info("REST request to delete LastPosition : {}", id);
        lastPositionService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.GONE);
    }


}
