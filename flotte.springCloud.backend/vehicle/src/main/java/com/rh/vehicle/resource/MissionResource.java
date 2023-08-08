package com.rh.vehicle.resource;

import com.rh.vehicle.domain.Mission;
import com.rh.vehicle.repository.MissionRepository;
import com.rh.vehicle.service.MissionService;
import com.rh.vehicle.util.HttpResponse;
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

import static org.springframework.http.HttpStatus.OK;

/**
 * REST controller for managing {@link Mission}.
 */
@RestController
@RequestMapping("/api/mission")
public class MissionResource {

    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    private static final String ENTITY_NAME = "mission";

    private final MissionService missionService;

    private final MissionRepository missionRepository;

    public MissionResource(MissionService missionService, MissionRepository missionRepository) {
        this.missionService = missionService;
        this.missionRepository = missionRepository;
    }

    @PostMapping("/mission")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Mission> createMission(@Valid @RequestBody Mission mission) throws URISyntaxException {
        LOGGER.info("REST request to save Mission : {}", mission);
        if (mission.getId() != null) {
            LOGGER.info("A new mission cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Mission result = missionService.save(mission);
        return new ResponseEntity<>(result, OK);
    }


    @PutMapping("/mission/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Mission> updateMission(@PathVariable(value = "id", required = false) final String id, @Valid @RequestBody Mission mission)
            throws URISyntaxException {
        LOGGER.info("REST request to update Mission : {}, {}", id, mission);
        if (mission.getId() == null) {
            LOGGER.info("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, mission.getId())) {
            LOGGER.info("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!missionRepository.existsById(id)) {
            LOGGER.info("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Mission result = missionService.save(mission);
        return new ResponseEntity(result, OK);
    }


    @PatchMapping(value = "/mission/{id}", consumes = {"application/json", "application/merge-patch+json"})
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Mission> partialUpdateMission(
            @PathVariable(value = "id", required = false) final String id,
            @NotNull @RequestBody Mission mission
    ) throws URISyntaxException {
        LOGGER.info("REST request to partial update Mission partially : {}, {}", id, mission);
        if (mission.getId() == null) {
            LOGGER.info("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, mission.getId())) {
            LOGGER.info("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!missionRepository.existsById(id)) {
            LOGGER.info("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Mission> result = missionService.partialUpdate(mission);

        return new ResponseEntity(result.get(), OK);
    }


    @GetMapping("/missions")
    @PreAuthorize("hasAuthority('USER')")
    public List<Mission> getAllMissions() {
        LOGGER.info("REST request to get all Missions");
        return missionService.findAll();
    }

    @GetMapping("/mission/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Mission> getMission(@PathVariable String id) {
        LOGGER.info("REST request to get Mission : {}", id);
        Optional<Mission> mission = missionService.findOne(id);
        return new ResponseEntity<>(mission.get(), OK);
    }


    @DeleteMapping("/mission/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<HttpResponse> deleteMission(@PathVariable String id) {
        LOGGER.info("REST request to delete Mission : {}", id);
        missionService.delete(id);
        return response(OK, "EtatVehicule_DELETED_SUCCESSFULLY");
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }
}
