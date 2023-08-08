package com.rh.vehicle.resource;

import com.rh.vehicle.domain.VehiculeTypes;
import com.rh.vehicle.repository.VehicleTypesRepository;
import com.rh.vehicle.service.VehicleTypesService;
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
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

/**
 * REST controller for managing {@link VehiculeTypes}.
 */
@RestController
@RequestMapping("/api/vehicle-types")
public class VehicleTypesResource {

    private final Logger log = LoggerFactory.getLogger(VehicleTypesResource.class);

    private static final String ENTITY_NAME = "vehicleTypes";
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final VehicleTypesService vehicleTypesService;
    private final VehicleTypesRepository vehicleTypesRepository;

    public VehicleTypesResource(VehicleTypesService vehicleTypesService, VehicleTypesRepository vehicleTypesRepository) {
        this.vehicleTypesService = vehicleTypesService;
        this.vehicleTypesRepository = vehicleTypesRepository;
    }


    @PostMapping("/vehicle-types")
    public ResponseEntity<VehiculeTypes> createVehicleTypes(@Valid @RequestBody VehiculeTypes vehicleTypes) throws URISyntaxException {
        LOGGER.info("REST request to save VehicleTypes : {}", vehicleTypes);
        if (vehicleTypes.getId() != null) {
            LOGGER.info("A new vehicleTypes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VehiculeTypes result = vehicleTypesService.save(vehicleTypes);
        return new ResponseEntity(result, OK);
    }

    @PutMapping("/vehicle-types/{id}")
    public ResponseEntity<VehiculeTypes> updateVehicleTypes(
            @PathVariable(value = "id", required = false) final String id,
            @Valid @RequestBody VehiculeTypes vehicleTypes
    ) throws URISyntaxException {
        LOGGER.info("REST request to update VehicleTypes : {}, {}", id, vehicleTypes);
        if (vehicleTypes.getId() == null) {
            LOGGER.info("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, vehicleTypes.getId())) {
            LOGGER.info("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!vehicleTypesRepository.existsById(id)) {
            LOGGER.info("Entity not found", ENTITY_NAME, "idnotfound");
        }

        VehiculeTypes result = vehicleTypesService.save(vehicleTypes);
        return new ResponseEntity(result, OK);
    }

    @PatchMapping(value = "/vehicle-types/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<VehiculeTypes> partialUpdateVehicleTypes(
            @PathVariable(value = "id", required = false) final String id,
            @NotNull @RequestBody VehiculeTypes vehicleTypes
    ) throws URISyntaxException {
        LOGGER.info("REST request to partial update VehicleTypes partially : {}, {}", id, vehicleTypes);
        if (vehicleTypes.getId() == null) {
            LOGGER.info("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, vehicleTypes.getId())) {
            LOGGER.info("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!vehicleTypesRepository.existsById(id)) {
            LOGGER.info("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<VehiculeTypes> result = vehicleTypesService.partialUpdate(vehicleTypes);

        return new ResponseEntity(result.get(), OK);
    }

    @GetMapping("/vehicle-types")
    public List<VehiculeTypes> getAllVehicleTypes() {
        LOGGER.info("REST request to get all VehicleTypes");
        return vehicleTypesService.findAll();
    }

    @GetMapping("/vehicle-types/{id}")
    public ResponseEntity<VehiculeTypes> getVehicleTypes(@PathVariable String id) {
        LOGGER.info("REST request to get VehicleTypes : {}", id);
        Optional<VehiculeTypes> vehicleTypes = vehicleTypesService.findOne(id);
        return new ResponseEntity<>(vehicleTypes.get(), OK);
    }

    @DeleteMapping("/vehicle-types/{id}")
    public ResponseEntity<HttpResponse> deleteVehicleTypes(@PathVariable String id) {
        LOGGER.info("REST request to delete VehicleTypes : {}", id);
        vehicleTypesService.delete(id);
        return response(OK, "VehicleTypesDELETED_SUCCESSFULLY");
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }
}
