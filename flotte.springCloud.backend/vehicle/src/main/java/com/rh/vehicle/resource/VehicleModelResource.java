package com.rh.vehicle.resource;

import com.rh.vehicle.domain.VehicleModel;
import com.rh.vehicle.repository.VehicleModelRepository;
import com.rh.vehicle.service.VehicleModelService;
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
 * REST controller for managing {@link VehicleModel}.
 */
@RestController
@RequestMapping("/api/vehicle-models")
public class VehicleModelResource {

    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    private static final String ENTITY_NAME = "vehicleModel";
    private final VehicleModelService vehicleModelService;
    private final VehicleModelRepository vehicleModelRepository;

    public VehicleModelResource(VehicleModelService vehicleModelService, VehicleModelRepository vehicleModelRepository) {
        this.vehicleModelService = vehicleModelService;
        this.vehicleModelRepository = vehicleModelRepository;
    }


    @PostMapping("/vehicle-models")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<VehicleModel> createVehicleModel(@Valid @RequestBody VehicleModel vehicleModel) throws URISyntaxException {
        LOGGER.info("REST request to save VehicleModel : {}", vehicleModel);
        if (vehicleModel.getId() != null) {
            LOGGER.info("A new vehicleModel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VehicleModel result = vehicleModelService.save(vehicleModel);
        return new ResponseEntity(result, OK);
    }


    @PutMapping("/vehicle-models/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<VehicleModel> updateVehicleModel(
            @PathVariable(value = "id", required = false) final String id,
            @Valid @RequestBody VehicleModel vehicleModel
    ) throws URISyntaxException {
        LOGGER.info("REST request to update VehicleModel : {}, {}", id, vehicleModel);
        if (vehicleModel.getId() == null) {
            LOGGER.info("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, vehicleModel.getId())) {
            LOGGER.info("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!vehicleModelRepository.existsById(id)) {
            LOGGER.info("Entity not found", ENTITY_NAME, "idnotfound");
        }

        VehicleModel result = vehicleModelService.save(vehicleModel);
        return new ResponseEntity(result, OK);
    }

    @PatchMapping(value = "/vehicle-models/{id}", consumes = {"application/json", "application/merge-patch+json"})
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<VehicleModel> partialUpdateVehicleModel(
            @PathVariable(value = "id", required = false) final String id,
            @NotNull @RequestBody VehicleModel vehicleModel
    ) throws URISyntaxException {
        LOGGER.info("REST request to partial update VehicleModel partially : {}, {}", id, vehicleModel);
        if (vehicleModel.getId() == null) {
            LOGGER.info("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, vehicleModel.getId())) {
            LOGGER.info("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!vehicleModelRepository.existsById(id)) {
            LOGGER.info("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<VehicleModel> result = vehicleModelService.partialUpdate(vehicleModel);

        return new ResponseEntity(result.get(), OK);
    }

    @GetMapping("/vehicle-models")
    @PreAuthorize("hasAuthority('USER')")
    public List<VehicleModel> getAllVehicleModels() {
        LOGGER.info("REST request to get all VehicleModels");
        return vehicleModelService.findAll();
    }

    @GetMapping("/vehicle-models/modelsBybrands/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public List<VehicleModel> getAllModelsByBrands(@PathVariable String id) {
        LOGGER.info("REST request to get all VehicleModels");

        return vehicleModelService.getAllModelsByBrands(id);
    }

    @GetMapping("/vehicle-models/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<VehicleModel> getVehicleModel(@PathVariable String id) {
        LOGGER.info("REST request to get VehicleModel : {}", id);
        Optional<VehicleModel> vehicleModel = vehicleModelService.findOne(id);
        return new ResponseEntity<>(vehicleModel.get(), OK);
    }


    @DeleteMapping("/vehicle-models/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<HttpResponse> deleteVehicleModel(@PathVariable String id) {
        LOGGER.info("REST request to delete VehicleModel : {}", id);
        vehicleModelService.delete(id);
        return response(OK, "EtatVehicule_DELETED_SUCCESSFULLY");
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }
}
