package com.rh.vehicle.resource;
import com.rh.vehicle.domain.Driver;


/**
 * REST controller for managing {@link Driver}.
 */

import com.rh.vehicle.domain.Vehicle;
import com.rh.vehicle.repository.DriverRepository;
import com.rh.vehicle.service.DriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import com.rh.vehicle.util.HttpResponse;

import static org.springframework.http.HttpStatus.NOT_IMPLEMENTED;
import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("/api/drivers")
public class DriverResource {
    private static final String ENTITY_NAME = "driver";
    Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final DriverService driverService;
    private  final DriverRepository driverRepository;

    public DriverResource(DriverService driverService, DriverRepository driverRepository) {
        this.driverService = driverService;
        this.driverRepository = driverRepository;
    }

    @PostMapping("/drivers")
    public ResponseEntity<Driver> createDriver(@RequestBody Driver driver) throws URISyntaxException {
        LOGGER.info("REST request to save Drivers : {}", driver);
        if (driver.getId() != null) {
            LOGGER.info("A new driver cannot already have an ID", ENTITY_NAME, "idexists");
        }
        System.out.println(driver.toString());
        Driver result = driverService.save(driver);
        return new ResponseEntity(result, OK);
    }

    @PutMapping("/drivers/{id}")
    public ResponseEntity<Driver> updateDrivers(
            @PathVariable(value = "id", required = false) final String id,
            @Valid @RequestBody Driver driver
    ) throws URISyntaxException {
        LOGGER.info("REST request to update Drivers : {}, {}", id, driver);
        if (driver.getId() == null) {
            LOGGER.info("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, driver.getId())) {
            LOGGER.info("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!driverRepository.existsById(id)) {
            LOGGER.info("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Driver result = driverService.save(driver);
        return new ResponseEntity(result, OK);
    }

    @PatchMapping(value = "/drivers/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<Driver> partialUpdateDriver(
            @PathVariable(value = "id", required = false) final String id,
            @NotNull @RequestBody Driver driver
    ) throws URISyntaxException {
        LOGGER.info("REST request to partial update Drivers partially : {}, {}", id, driver);
        if (driver.getId() == null) {
            LOGGER.info("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, driver.getId())) {
            LOGGER.info("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!driverRepository.existsById(id)) {
            LOGGER.info("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Driver> result = driverService.partialUpdate(driver);

        return new ResponseEntity(result.get(), OK);
    }

    @GetMapping("/drivers")
    public List<Driver> getAllDrivers() {
        LOGGER.info("REST request to get all Drivers");
        return driverService.findAll();
    }

    @GetMapping("/drivers/{id}")
    public ResponseEntity<Driver> getDrivers(@PathVariable String id) {
        LOGGER.info("REST request to get Driver : {}", id);
        Optional<Driver> driver = driverService.findOne(id);
        if(driver.isPresent()){
            return new ResponseEntity<>(driver.get(), OK);
        }else {
            return null;
        }

    }
    @GetMapping("/drivers/mle/{mle}")
    public ResponseEntity<Driver> getDriversByMle(@PathVariable int mle) {
        LOGGER.info("REST request to get Driver : {}", mle);

        Optional<Driver> driver = driverService.findByMle(mle);
        return new ResponseEntity<>(driver.get(), OK);
    }


    @DeleteMapping("/drivers/{id}")
    public ResponseEntity<HttpResponse> deleteDriver(@PathVariable String id) {
        LOGGER.info("REST request to delete Driver : {}", id);
        driverService.delete(id);
        return response(OK, "Driver_DELETED_SUCCESSFULLY");
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }

}
