package com.rh.vehicle.resource;

import com.rh.vehicle.domain.Fuel;
import com.rh.vehicle.repository.FuelRepository;
import com.rh.vehicle.service.FuelService;
import com.rh.vehicle.util.HttpResponse;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

/**
 * REST controller for managing {@link Fuel}.
 */
@RestController
@RequestMapping("/api/fuels")
public class FuelResource {

    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    private static final String ENTITY_NAME = "fuel";

    private final FuelService fuelService;

    private final FuelRepository fuelRepository;

    public FuelResource(FuelService fuelService, FuelRepository fuelRepository) {
        this.fuelService = fuelService;
        this.fuelRepository = fuelRepository;
    }

    @PostMapping("/fuels")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Fuel> createFuel(@Valid @RequestBody Fuel fuel) throws URISyntaxException {
        LOGGER.info("REST request to save Fuel : {}", fuel);
        if (fuel.getId() != null) {
            LOGGER.info("A new fuel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Fuel result = fuelService.save(fuel);
        return new ResponseEntity<>(result, OK);
    }


    @PutMapping("/fuels/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Fuel> updateFuel(@PathVariable(value = "id", required = false) final String id, @Valid @RequestBody Fuel fuel)
            throws URISyntaxException {
        LOGGER.info("REST request to update Fuel : {}, {}", id, fuel);
        if (fuel.getId() == null) {
            LOGGER.info("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fuel.getId())) {
            LOGGER.info("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fuelRepository.existsById(id)) {
            LOGGER.info("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Fuel result = fuelService.save(fuel);
        return new ResponseEntity(result, OK);
    }


    @PatchMapping(value = "/fuels/{id}", consumes = {"application/json", "application/merge-patch+json"})
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Fuel> partialUpdateFuel(
            @PathVariable(value = "id", required = false) final String id,
            @NotNull @RequestBody Fuel fuel
    ) throws URISyntaxException {
        LOGGER.info("REST request to partial update Fuel partially : {}, {}", id, fuel);
        if (fuel.getId() == null) {
            LOGGER.info("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fuel.getId())) {
            LOGGER.info("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fuelRepository.existsById(id)) {
            LOGGER.info("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Fuel> result = fuelService.partialUpdate(fuel);

        return new ResponseEntity(result.get(), OK);
    }


    @GetMapping("/fuels")
    @PreAuthorize("hasAuthority('USER')")
    public List<Fuel> getAllFuels() {
        LOGGER.info("REST request to get all Fuels");
        return fuelService.findAll();
    }

    @GetMapping("/fuels/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Fuel> getFuel(@PathVariable String id) {
        LOGGER.info("REST request to get Fuel : {}", id);
        Optional<Fuel> fuel = fuelService.findOne(id);
        return new ResponseEntity<>(fuel.get(), OK);
    }


    @DeleteMapping("/fuels/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<HttpResponse> deleteFuel(@PathVariable String id) {
        LOGGER.info("REST request to delete Fuel : {}", id);
        fuelService.delete(id);
        return response(OK, "EtatVehicule_DELETED_SUCCESSFULLY");
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }
}
