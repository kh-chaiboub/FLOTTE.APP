package com.rh.vehicle.resource;

import com.rh.vehicle.domain.EtatVehicule;
import com.rh.vehicle.repository.EtatVehiculeRepository;
import com.rh.vehicle.service.EtatVehiculeService;
import com.rh.vehicle.util.HttpResponse;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

/**
 * REST controller for managing {@link EtatVehicule}.
 */
@RestController
@RequestMapping("/api/vehicle-status")
public class EtatVehiculeResource {

    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    private static final String ENTITY_NAME = "VehiculeStatus";


    private final EtatVehiculeService etatVehiculeService;

    private final EtatVehiculeRepository etatVehiculeRepository;

    public EtatVehiculeResource(EtatVehiculeService etatVehiculeService, EtatVehiculeRepository etatVehiculeRepository) {
        this.etatVehiculeService = etatVehiculeService;
        this.etatVehiculeRepository = etatVehiculeRepository;
    }


    @PostMapping("/vehicle-status")
    public ResponseEntity<EtatVehicule> createEtatVehicule(@RequestBody EtatVehicule etatVehicule) throws URISyntaxException {
        LOGGER.info("REST request to save EtatVehicule : {}", etatVehicule);
        if (etatVehicule.getId() != null) {
            LOGGER.info("A new etatVehicule cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EtatVehicule result = etatVehiculeService.save(etatVehicule);
        return new ResponseEntity(result, OK);
    }


    @PutMapping("/vehicle-status/{id}")
    public ResponseEntity<EtatVehicule> updateEtatVehicule(
            @PathVariable(value = "id", required = false) final String id,
            @RequestBody EtatVehicule etatVehicule
    ) throws URISyntaxException {
        LOGGER.info("REST request to update vehicle-status : {}, {}", id, etatVehicule);
        if (etatVehicule.getId() == null) {
            LOGGER.info("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, etatVehicule.getId())) {
            LOGGER.info("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!etatVehiculeRepository.existsById(id)) {
            LOGGER.info("Entity not found", ENTITY_NAME, "idnotfound");
        }

        EtatVehicule result = etatVehiculeService.save(etatVehicule);
        return new ResponseEntity(result, OK);
    }


    @PatchMapping(value = "/vehicle-status/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<EtatVehicule> partialUpdateEtatVehicule(
            @PathVariable(value = "id", required = false) final String id,
            @RequestBody EtatVehicule etatVehicule
    ) throws URISyntaxException {
        LOGGER.info("REST request to partial update vehicle-status partially : {}, {}", id, etatVehicule);
        if (etatVehicule.getId() == null) {
            LOGGER.info("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, etatVehicule.getId())) {
            LOGGER.info("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!etatVehiculeRepository.existsById(id)) {
            LOGGER.info("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EtatVehicule> result = etatVehiculeService.partialUpdate(etatVehicule);

        return new ResponseEntity(result.get(), OK);
    }


    @GetMapping("/vehicle-status")
    public List<EtatVehicule> getAllEtatVehicules() {
        LOGGER.info("REST request to get all vehicle-status");
        return etatVehiculeService.findAll();
    }


    @GetMapping("/vehicle-status/{id}")
    public ResponseEntity<EtatVehicule> getEtatVehicule(@PathVariable String id) {
        LOGGER.info("REST request to get EtatVehicule : {}", id);
        Optional<EtatVehicule> etatVehicule = etatVehiculeService.findOne(id);
        return new ResponseEntity<>(etatVehicule.get(), OK);
    }


    @DeleteMapping("/vehicle-status/{id}")
    public ResponseEntity<HttpResponse> deleteEtatVehicule(@PathVariable String id) {
        LOGGER.info("REST request to delete vehicle-status : {}", id);
        etatVehiculeService.delete(id);
        return response(OK, "vehicle-status_DELETED_SUCCESSFULLY");
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }
}
