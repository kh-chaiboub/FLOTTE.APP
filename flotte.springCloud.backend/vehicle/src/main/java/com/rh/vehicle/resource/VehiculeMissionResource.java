package com.rh.vehicle.resource;




import com.rh.vehicle.domain.VehiculeMission;
import com.rh.vehicle.repository.VehiculeMissionRepository;
import com.rh.vehicle.service.VehicleMissionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import com.rh.vehicle.util.HttpResponse;

import static org.springframework.http.HttpStatus.OK;

/**
 * REST controller for managing {@link VehiculeMission}.
 */
@RestController
@RequestMapping("/api/vehicle-missions")
public class VehiculeMissionResource {
    private final Logger log = LoggerFactory.getLogger(VehiculeMissionResource.class);
    private static final String ENTITY_NAME = "vehicleMissions";
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final VehicleMissionsService vehicleMissionsService;
    private final VehiculeMissionRepository vehiculeMissionRepository;


    public VehiculeMissionResource(VehicleMissionsService vehicleMissionsService, VehiculeMissionRepository vehiculeMissionRepository) {
        this.vehicleMissionsService = vehicleMissionsService;
        this.vehiculeMissionRepository = vehiculeMissionRepository;
    }

    @PostMapping("/vehicle-missions")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<VehiculeMission> createVehicleMissions(@Valid @RequestBody VehiculeMission vehiculeMission) throws URISyntaxException {
        LOGGER.info("REST request to save VehiculeMissions : {}", vehiculeMission);
        if (vehiculeMission.getId() != null) {
            LOGGER.info("A new vehiculeMission cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VehiculeMission result = vehicleMissionsService.save(vehiculeMission);
        return new ResponseEntity(result, OK);
    }


    @PutMapping("/vehicle-missions/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<VehiculeMission> updateVehicleMissions(
            @PathVariable(value = "id", required = false) final String id,
            @Valid @RequestBody VehiculeMission vehiculeMission
    ) throws URISyntaxException {
        LOGGER.info("REST request to update VehiculeMissions : {}, {}", id, vehiculeMission);
        if (vehiculeMission.getId() == null) {
            LOGGER.info("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, vehiculeMission.getId())) {
            LOGGER.info("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!vehiculeMissionRepository.existsById(id)) {
            LOGGER.info("Entity not found", ENTITY_NAME, "idnotfound");
        }

        VehiculeMission result = vehicleMissionsService.save(vehiculeMission);
        return new ResponseEntity(result, OK);
    }
    @PatchMapping(value = "/vehicle-missions/{id}", consumes = {"application/json", "application/merge-patch+json"})
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<VehiculeMission> partialUpdateVehicleMissions(
            @PathVariable(value = "id", required = false) final String id,
            @NotNull @RequestBody VehiculeMission vehiculeMission
    ) throws URISyntaxException {
        LOGGER.info("REST request to partial update vehiculeMission partially : {}, {}", id, vehiculeMission);
        if (vehiculeMission.getId() == null) {
            LOGGER.info("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, vehiculeMission.getId())) {
            LOGGER.info("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!vehiculeMissionRepository.existsById(id)) {
            LOGGER.info("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<VehiculeMission> result = vehicleMissionsService.partialUpdate(vehiculeMission);

        return new ResponseEntity(result.get(), OK);
    }

    @GetMapping("/vehicle-missions")
    @PreAuthorize("hasAuthority('USER')")
    public List<VehiculeMission> getAllVehicleMissions() {
        LOGGER.info("REST request to get all VehicleTypes");
        return vehicleMissionsService.findAll();
    }

    @GetMapping("/vehicle-missions/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<VehiculeMission> getVehicleMissions(@PathVariable String id) {
        LOGGER.info("REST request to get VehiculeMission : {}", id);
        Optional<VehiculeMission> vehiculeMission = vehicleMissionsService.findOne(id);
        return new ResponseEntity<>(vehiculeMission.get(), OK);
    }
    @DeleteMapping("/vehicle-missions/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<HttpResponse> deleteVehicleMissions(@PathVariable String id) {
        LOGGER.info("REST request to delete vehiculeMission : {}", id);
        vehicleMissionsService.delete(id);
        return response(OK, "VehicleTypesDELETED_SUCCESSFULLY");
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }

}
