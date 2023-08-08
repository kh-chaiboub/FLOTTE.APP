package com.rh.vehicle.resource;

import com.rh.vehicle.domain.Vehicle;
import com.rh.vehicle.domain.VehicleNew;
import com.rh.vehicle.model.User;
import com.rh.vehicle.model.UserClient;
import com.rh.vehicle.repository.VehicleRepository;
import com.rh.vehicle.service.VehicleService;
import com.rh.vehicle.service.VehiculeProjectionListMap;
import com.rh.vehicle.util.HttpResponse;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
//import reactor.core.publisher.Flux;

import static org.springframework.http.HttpStatus.OK;

/**
 * REST controller for managing {@link Vehicle}.
 */
@RestController
@RequestMapping("/api/vehicules")
public class VehicleResource {


    private static final String ENTITY_NAME = "vehicule";
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final VehicleService vehicleService;
    private final VehicleRepository vehicleRepository;

    @Autowired
    private UserClient userClient;

    public VehicleResource(UserClient userClient, VehicleService vehicleService, VehicleRepository vehicleRepository) {
        this.vehicleService = vehicleService;
        this.vehicleRepository = vehicleRepository;
        this.userClient = userClient;
    }

    @PostMapping("/vehicules")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Vehicle> createVehicle(@RequestHeader Map<String, String> headers,@RequestBody Vehicle vehicle) throws URISyntaxException {
        System.out.println("REST request to save Vehicle:" + vehicle);
        LOGGER.info("REST request to save Vehicle by "+headers+": {}", vehicle);
        if (vehicle.getId() != null) {
            LOGGER.info(headers+"A new vehicle cannot already have an ID", ENTITY_NAME, "exists");
        }
        Vehicle result = null;
        result = vehicleService.save(vehicle);
        return new ResponseEntity(result, OK);


    }


    @PutMapping("/vehicules/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Vehicle> updateVehicle(
            @PathVariable(value = "id", required = false) final String id,
            @Valid @RequestBody Vehicle vehicle,
            @RequestHeader Map<String, String> headers
    ) throws URISyntaxException {
        LOGGER.info("REST request to update Vehicle  by" + headers + " : ", id, vehicle);

        if (vehicle.getId() == null) {
            LOGGER.info(headers +"Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, vehicle.getId())) {
            LOGGER.info(headers +"Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!vehicleRepository.existsById(id)) {
            LOGGER.info(headers +"Entity not found", ENTITY_NAME, "idnotfound");
        }
        Vehicle result = null;
            result = vehicleService.save(vehicle);
            return new ResponseEntity(result, OK);


    }

    @PatchMapping(value = "/vehicules/{id}", consumes = {"application/json", "application/merge-patch+json"})
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Vehicle> partialUpdateVehicle(
            @PathVariable(value = "id", required = false) final String id,
            @NotNull @RequestBody Vehicle vehicle
            , @RequestHeader Map<String, String> headers) throws URISyntaxException {
        LOGGER.info("REST request to partial update Vehicle partially by" + headers + " : ", id, vehicle);
        if (vehicle.getId() == null) {
            LOGGER.info(headers + "Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, vehicle.getId())) {
            LOGGER.info(headers + "Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!vehicleRepository.existsById(id)) {
            LOGGER.info(headers + "Entity not found", ENTITY_NAME, "idnotfound");
        }
        Optional<Vehicle> result = null;

            result = vehicleService.partialUpdate(vehicle);
            return new ResponseEntity(result.get(), OK);


    }

    @GetMapping("/vehicules")
    @PreAuthorize("hasAuthority('USER')")
    public List<Vehicle> getAllVehicles(@RequestHeader Map<String, String> headers) {
        System.out.println(headers);
        LOGGER.info("REST request to get all Vehicles");
        LOGGER.info("REST request to get all Vehicles by  " + headers);
        List<Vehicle> vls = vehicleService. findAll(null);
        return vls;

    }
    @GetMapping("/projection/vehicules")
    @PreAuthorize("hasAuthority('USER')")
    public List<VehiculeProjectionListMap> getAllVehiclesProjectionDashboard(
            @RequestParam(value="page",defaultValue = "0") int page,
            @RequestParam(value="size",defaultValue = "50") int size,
            @RequestHeader Map<String, String> headers) {

        LOGGER.info("REST request to get all Vehicles");

        Pageable secondPageWithFiveElements = PageRequest.of(page, size);
        List<VehiculeProjectionListMap> vls = vehicleService.findAllProjection(secondPageWithFiveElements,null);
        return vls;

    }
    @GetMapping("/projection/vehiculeInmaps")
    @PreAuthorize("hasAuthority('USER')")
    public List<VehiculeProjectionListMap> getAllVehiclesProjection(
            @RequestParam(value="page",defaultValue = "0") int page,
            @RequestParam(value="size",defaultValue = "50") int size,
             @RequestHeader Map<String, String> headers) {
        System.out.println(headers);
        LOGGER.info("REST request to get all Vehicles");

        LOGGER.info("REST request to get all Vehicles by  " + headers);
        Pageable secondPageWithFiveElements = PageRequest.of(page, size);
        List<VehiculeProjectionListMap> vls = vehicleService.findAllProjection(secondPageWithFiveElements,null);
        return vls;

    }

    @GetMapping("/projection/vehiculeInmaps/status/true")
    @PreAuthorize("hasAuthority('USER')")
    public List<VehiculeProjectionListMap> getAllVehiclesProjectionStatusTrue(
            @RequestParam(value="page",defaultValue = "0") int page,
            @RequestParam(value="size",defaultValue = "50") int size,
            @RequestHeader Map<String, String> headers) {
        System.out.println(headers);
        LOGGER.info("REST request to get all Vehicles");

        LOGGER.info("REST request to get all Vehicles by  " + headers);
        Pageable secondPageWithFiveElements = PageRequest.of(page, size);
        List<VehiculeProjectionListMap> vls = vehicleService.findAllProjectionStatusTrue(secondPageWithFiveElements,null);
        return vls;
    }

    @GetMapping("/projection/vehiculeInmaps/status/false")
    @PreAuthorize("hasAuthority('USER')")
    public List<VehiculeProjectionListMap> getAllVehiclesProjectionStatusFalse(
            @RequestParam(value="page",defaultValue = "0") int page,
            @RequestParam(value="size",defaultValue = "50") int size,
            @RequestHeader Map<String, String> headers) {
        System.out.println(headers);
        LOGGER.info("REST request to get all Vehicles");

        LOGGER.info("REST request to get all Vehicles by  " + headers);
        Pageable pageable = PageRequest.of(page, size);
        List<VehiculeProjectionListMap> vls = vehicleService.findAllProjectionStatusFalse(pageable,null);
        return vls;

    }


    @GetMapping("/search/containsByImm")
    @PreAuthorize("hasAuthority('USER')")
    public List<VehiculeProjectionListMap> findByRegistrationNumberContains(@Param("imm") String imm, @RequestHeader Map<String, String> headers) {
        System.out.println(headers);
        LOGGER.info("REST request to get all Vehicles");
        LOGGER.info("REST request to get all Vehicles by  " + headers);
        Pageable secondPageWithFiveElements = PageRequest.of(0, 50);
        List<VehiculeProjectionListMap> vls = vehicleService.findByRegistrationNumberContains(imm,secondPageWithFiveElements,null);
        return vls;

    }


    @GetMapping("/vehicules/itemstreeview")
    @PreAuthorize("hasAuthority('USER')")
    public List<VehicleNew> getAllVehiclesItemstreeview(@RequestHeader Map<String, String> headers) {
        LOGGER.info("REST request to get all Vehicles");
        LOGGER.info("REST request to get all Vehicles by  " + headers);
        List<VehicleNew> vls = vehicleService.findAllItemstreeview(null);
        return vls;
    }


    @GetMapping("/list/status/false")
    @PreAuthorize("hasAuthority('USER')")
    public List<Vehicle> getVehiclesWithDeviceStatusFalse() {
        return vehicleService.findByDeviceStatusFalse();
    }
    @GetMapping("/count/status/false")
    @PreAuthorize("hasAuthority('USER')")
    public int getCountVehiclesWithDeviceStatusFalse() {
        return vehicleService.countByDeviceStatusFalse();
    }
    @GetMapping("/list/status/true")
    @PreAuthorize("hasAuthority('USER')")
    public List<Vehicle> getVehiclesWithDeviceStatusTrue() {
        return vehicleService.findByDeviceStatusTrue();
    }
    @GetMapping("/count/status/true")
    @PreAuthorize("hasAuthority('USER')")
    public int getCountVehiclesWithDeviceStatusTrue() {
        return vehicleService.countByDeviceStatusTrue();
    }

    @GetMapping("/count/vehicules/notDote")
    @PreAuthorize("hasAuthority('USER')")
    public int countVehicleByNotDoteDevice(@RequestHeader Map<String, String> headers){
            return vehicleService.countVehicleByNotDoteDevice();
    }



    @GetMapping("/vehicules/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Vehicle> getVehicle(@RequestHeader Map<String, String> headers, @PathVariable String id) {
        LOGGER.info("REST request to get Vehicle : {}", id);
        Optional<Vehicle> vehicle = null;
            vehicle = vehicleService.findOne(null, id);
            LOGGER.info("REST request to get Vehicle :by", headers);

        return new ResponseEntity<>(vehicle.get(), OK);
    }


    @DeleteMapping("/vehicules/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<HttpResponse> deleteVehicle(@PathVariable String id, @RequestHeader Map<String, String> headers) {
        LOGGER.info("REST request to delete Vehicle : {}", id);
            vehicleService.delete(id);
            return response(OK, "Vehicule_DELETED_SUCCESSFULLY" + headers);
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }

}
