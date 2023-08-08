package com.rh.vehicle.resource;

import com.rh.vehicle.domain.VehicleBrands;
import com.rh.vehicle.repository.VehicleBrandsRepository;
import com.rh.vehicle.service.VehicleBrandsService;
import com.rh.vehicle.util.HttpResponse;

import java.net.URI;
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
 * REST controller for managing {@link VehicleBrands}.
 */
@RestController
@RequestMapping("/api/vehicle-brands")
public class VehicleBrandsResource {


    private static final String ENTITY_NAME = "vehicleBrands";

    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final VehicleBrandsService vehicleBrandsService;

    private final VehicleBrandsRepository vehicleBrandsRepository;

    public VehicleBrandsResource(VehicleBrandsService vehicleBrandsService, VehicleBrandsRepository vehicleBrandsRepository) {
        this.vehicleBrandsService = vehicleBrandsService;
        this.vehicleBrandsRepository = vehicleBrandsRepository;
    }

    @PostMapping("/vehicle-brands")
    public ResponseEntity<VehicleBrands> createVehicleBrands(@Valid @RequestBody VehicleBrands vehicleBrands) throws URISyntaxException {
        LOGGER.info("REST request to save VehicleBrands : {}", vehicleBrands);
        if (vehicleBrands.getId() != null) {
            LOGGER.info("A new vehicleBrands cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VehicleBrands result = vehicleBrandsService.save(vehicleBrands);
        return new ResponseEntity(result, OK);
    }


    @PutMapping("/vehicle-brands/{id}")
    public ResponseEntity<VehicleBrands> updateVehicleBrands(
            @PathVariable(value = "id", required = false) final String id,
            @Valid @RequestBody VehicleBrands vehicleBrands
    ) throws URISyntaxException {
        LOGGER.info("REST request to update VehicleBrands : {}, {}", id, vehicleBrands);
        if (vehicleBrands.getId() == null) {
            LOGGER.info("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, vehicleBrands.getId())) {
            LOGGER.info("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!vehicleBrandsRepository.existsById(id)) {
            LOGGER.info("Entity not found", ENTITY_NAME, "idnotfound");
        }

        VehicleBrands result = vehicleBrandsService.save(vehicleBrands);
        return new ResponseEntity(result, OK);
    }


    @PatchMapping(value = "/vehicle-brands/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<VehicleBrands> partialUpdateVehicleBrands(
            @PathVariable(value = "id", required = false) final String id,
            @NotNull @RequestBody VehicleBrands vehicleBrands
    ) throws URISyntaxException {
        LOGGER.info("REST request to partial update VehicleBrands partially : {}, {}", id, vehicleBrands);
        if (vehicleBrands.getId() == null) {
            LOGGER.info("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, vehicleBrands.getId())) {
            LOGGER.info("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!vehicleBrandsRepository.existsById(id)) {
            LOGGER.info("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<VehicleBrands> result = vehicleBrandsService.partialUpdate(vehicleBrands);

        return new ResponseEntity(result.get(), OK);
    }


    @GetMapping("/vehicle-brands")
    public List<VehicleBrands> getAllVehicleBrands() {
        LOGGER.info("REST request to get all VehicleBrands");
        return vehicleBrandsService.findAll();
    }


    @GetMapping("/vehicle-brands/{id}")
    public ResponseEntity<VehicleBrands> getVehicleBrands(@PathVariable String id) {
        LOGGER.info("REST request to get VehicleBrands : {}", id);
        Optional<VehicleBrands> vehicleBrands = vehicleBrandsService.findOne(id);
        return new ResponseEntity<>(vehicleBrands.get(), OK);
    }

    @GetMapping("/vehicle-brands/byType/{id}")
    public List<VehicleBrands> getVehicleBrandsByType(@PathVariable String id) {
        LOGGER.info("REST request to get VehicleBrands By Type : {}", id);
        return vehicleBrandsService.findBrandsByType(id);

    }


    @DeleteMapping("/vehicle-brands/{id}")
    public ResponseEntity<HttpResponse> deleteVehicleBrands(@PathVariable String id) {
        LOGGER.info("REST request to delete VehicleBrands : {}", id);
        vehicleBrandsService.delete(id);
        return response(OK, "EtatVehicule_DELETED_SUCCESSFULLY");
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }
}
