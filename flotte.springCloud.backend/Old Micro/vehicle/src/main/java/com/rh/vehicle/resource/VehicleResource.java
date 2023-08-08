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

    public static boolean userHasAuthority(String authority) {
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        for (GrantedAuthority grantedAuthority : authorities) {
            if (authority.equals(grantedAuthority.getAuthority())) {
                return true;
            }
        }

        return false;
    }

    public Optional<User> getUserProfil() {
        String idUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Optional<User> user = userClient.findUserById(idUser);
        System.out.println(user);
        return user;
    }


    @PostMapping("/vehicules")
    public ResponseEntity<Vehicle> createVehicle(@RequestHeader Map<String, String> headers,@RequestBody Vehicle vehicle) throws URISyntaxException {
        System.out.println("REST request to save Vehicle:" + vehicle);
        LOGGER.info("REST request to save Vehicle by "+headers+": {}", vehicle);
        if (vehicle.getId() != null) {
            LOGGER.info(headers+"A new vehicle cannot already have an ID", ENTITY_NAME, "exists");
        }

        Vehicle result = null;
        if (userHasAuthority("ADMIN")) {

                result = vehicleService.save(vehicle);

            return new ResponseEntity(result, OK);

        } else {
            for (String authoritUser : getUserProfil().get().getAuthorities()) {
                if (authoritUser.equals(vehicle.getPrefOrgan())) {
                    result = vehicleService.save(vehicle);

                }
            }
            return new ResponseEntity(result, OK);
        }

    }


    @PutMapping("/vehicules/{id}")
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
        if (userHasAuthority("ADMIN")) {
            result = vehicleService.save(vehicle);
            return new ResponseEntity(result, OK);

        } else {
            for (String authoritUser : getUserProfil().get().getAuthorities()) {
                if (authoritUser.equals(vehicle.getPrefOrgan())) {
                    result = vehicleService.save(vehicle);

                }
            }
            return new ResponseEntity(result, OK);
        }

    }

    @PatchMapping(value = "/vehicules/{id}", consumes = {"application/json", "application/merge-patch+json"})
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
        if (userHasAuthority("ADMIN")) {
            result = vehicleService.partialUpdate(vehicle);
            return new ResponseEntity(result.get(), OK);

        } else {
            for (String authoritUser : getUserProfil().get().getAuthorities()) {
                if (authoritUser.equals(vehicle.getPrefOrgan())) {
                    result = vehicleService.partialUpdate(vehicle);

                }
            }
            return new ResponseEntity(result.get(), OK);
        }


    }

    @GetMapping("/vehicules")
    public List<Vehicle> getAllVehicles(@RequestHeader Map<String, String> headers) {
        System.out.println("===========================================");
        System.out.println(headers);
        LOGGER.info("REST request to get all Vehicles");
        if (userHasAuthority("ADMIN")) {
            LOGGER.info("REST request to get all Vehicles by  " + headers);
            List<Vehicle> vls = vehicleService. findAll(null);

            return vls;
        } else {
            LOGGER.info("REST request to get all Vehicles by " + headers);
            return vehicleService.findAllByRefOrgans(null, getUserProfil().get());
        }
    }
    @GetMapping("/projection/vehicules")
    public List<VehiculeProjectionListMap> getAllVehiclesProjectionDashboard(
            @RequestParam(value="page",defaultValue = "0") int page,
            @RequestParam(value="size",defaultValue = "50") int size,
            @RequestHeader Map<String, String> headers) {

        LOGGER.info("REST request to get all Vehicles");
        if (userHasAuthority("ADMIN")) {
            Pageable secondPageWithFiveElements = PageRequest.of(page, size);
            List<VehiculeProjectionListMap> vls = vehicleService.findAllProjection(secondPageWithFiveElements,null);

            return vls;
        } else {
            LOGGER.info("REST request to get all Vehicles by " );
            return null; }
    }
    @GetMapping("/projection/vehiculeInmaps")
    public List<VehiculeProjectionListMap> getAllVehiclesProjection(
            @RequestParam(value="page",defaultValue = "0") int page,
            @RequestParam(value="size",defaultValue = "50") int size,
             @RequestHeader Map<String, String> headers) {
        System.out.println("===========================================");
        System.out.println(headers);
        LOGGER.info("REST request to get all Vehicles");
        if (userHasAuthority("ADMIN")) {
            LOGGER.info("REST request to get all Vehicles by  " + headers);
            Pageable secondPageWithFiveElements = PageRequest.of(page, size);
            List<VehiculeProjectionListMap> vls = vehicleService.findAllProjection(secondPageWithFiveElements,null);

            return vls;
        } else {
            LOGGER.info("REST request to get all Vehicles by " + headers);
            return null; }
    }

    @GetMapping("/projection/vehiculeInmaps/status/true")
    public List<VehiculeProjectionListMap> getAllVehiclesProjectionStatusTrue(
            @RequestParam(value="page",defaultValue = "0") int page,
            @RequestParam(value="size",defaultValue = "50") int size,
            @RequestHeader Map<String, String> headers) {
        System.out.println("===========================================");
        System.out.println(headers);
        LOGGER.info("REST request to get all Vehicles");
        if (userHasAuthority("ADMIN")) {
            LOGGER.info("REST request to get all Vehicles by  " + headers);
            Pageable secondPageWithFiveElements = PageRequest.of(page, size);
            List<VehiculeProjectionListMap> vls = vehicleService.findAllProjectionStatusTrue(secondPageWithFiveElements,null);

            return vls;
        } else {
            LOGGER.info("REST request to get all Vehicles by " + headers);
            return null; }
    }

    @GetMapping("/projection/vehiculeInmaps/status/false")
    public List<VehiculeProjectionListMap> getAllVehiclesProjectionStatusFalse(
            @RequestParam(value="page",defaultValue = "0") int page,
            @RequestParam(value="size",defaultValue = "50") int size,
            @RequestHeader Map<String, String> headers) {
        System.out.println("===========================================");
        System.out.println(headers);
        LOGGER.info("REST request to get all Vehicles");
        if (userHasAuthority("ADMIN")) {
            LOGGER.info("REST request to get all Vehicles by  " + headers);
            Pageable pageable = PageRequest.of(page, size);
            List<VehiculeProjectionListMap> vls = vehicleService.findAllProjectionStatusFalse(pageable,null);

            return vls;
        } else {
            LOGGER.info("REST request to get all Vehicles by " + headers);
            return null; }
    }


    @GetMapping("/search/containsByImm")
    public List<VehiculeProjectionListMap> findByRegistrationNumberContains(@Param("imm") String imm, @RequestHeader Map<String, String> headers) {
        System.out.println("===========================================");
        System.out.println(headers);
        LOGGER.info("REST request to get all Vehicles");
        if (userHasAuthority("ADMIN")) {
            LOGGER.info("REST request to get all Vehicles by  " + headers);
            Pageable secondPageWithFiveElements = PageRequest.of(0, 50);
            List<VehiculeProjectionListMap> vls = vehicleService.findByRegistrationNumberContains(imm,secondPageWithFiveElements,null);

            return vls;
        } else {
            LOGGER.info("REST request to get all Vehicles by " + headers);
            return null; }
    }


    @GetMapping("/vehicules/itemstreeview")
    public List<VehicleNew> getAllVehiclesItemstreeview(@RequestHeader Map<String, String> headers) {
        System.out.println("===========================================");
        System.out.println(headers);
        LOGGER.info("REST request to get all Vehicles");
        if (userHasAuthority("ADMIN")) {
            LOGGER.info("REST request to get all Vehicles by  " + headers);
            List<VehicleNew> vls = vehicleService.findAllItemstreeview(null);

            return vls;
        } else {
            LOGGER.info("REST request to get all Vehicles by " + headers);
           // return vehicleService.findAllByRefOrgans(null, getUserProfil().get());
            return null;
        }
    }


//    @GetMapping("/count/vehicules/status/{b}")
//    public int countVehicleByDeviceStatus(@PathVariable boolean b,@RequestHeader Map<String, String> headers){
//        if (userHasAuthority("ADMIN")) {
//
//            return vehicleService.countVehicleByDevice(b);
//        }else {
//            return vehicleService.countVehicleByDeviceByRefOrgan(getUserProfil().get(),b);
//        }
//
//
//    }
    @GetMapping("/list/status/false")
    public List<Vehicle> getVehiclesWithDeviceStatusFalse() {
        return vehicleService.findByDeviceStatusFalse();
    }
    @GetMapping("/count/status/false")
    public int getCountVehiclesWithDeviceStatusFalse() {
        return vehicleService.countByDeviceStatusFalse();
    }
    @GetMapping("/list/status/true")
    public List<Vehicle> getVehiclesWithDeviceStatusTrue() {
        return vehicleService.findByDeviceStatusTrue();
    }
    @GetMapping("/count/status/true")
    public int getCountVehiclesWithDeviceStatusTrue() {
        return vehicleService.countByDeviceStatusTrue();
    }

    @GetMapping("/count/vehicules/notDote")
    public int countVehicleByNotDoteDevice(@RequestHeader Map<String, String> headers){
        if (userHasAuthority("ADMIN")) {

            return vehicleService.countVehicleByNotDoteDevice();

        }else {
            return vehicleService.countVehicleByNotDoteDeviceByRefOrgan(getUserProfil().get());
        }


    }

//    @GetMapping("/count/vehicules/notDote")
//    public int countVehicleByNotDoteDevice(@RequestHeader Map<String, String> headers){
//        // if (userHasAuthority("ADMIN")) {
//
//        return vehicleService.ListVehicleByNotDoteDevice().size();
//
////        }else {
////            return vehicleService.countVehicleByNotDoteDeviceByRefOrgan(getUserProfil().get());
////        }
//
//
//    }

//    @GetMapping("/count/vehicules/After24Hours")
//    public int countVehiclesWithDeviceTimeAfter24Hours(@RequestHeader Map<String, String> headers){
//        // if (userHasAuthority("ADMIN")) {
//
//        return vehicleService.countVehiclesWithDeviceTimeAfter24Hours();
//
////        }else {
////            return vehicleService.countVehicleByNotDoteDeviceByRefOrgan(getUserProfil().get());
////        }countVehiclesWithDeviceTimeAfter
//
//
//    }



//    @GetMapping("/list/vehicules/notDote")
//    public List<Vehicle>VehicleByNotDoteDeviceList(@RequestHeader Map<String, String> headers){
//       // if (userHasAuthority("ADMIN")) {
//
//            return vehicleService.ListVehicleByNotDoteDevice();
//
////        }else {
////            return vehicleService.countVehicleByNotDoteDeviceByRefOrgan(getUserProfil().get());
////        }
//
//
//    }

//    @GetMapping("/vehicules/byRef")
//    public List<Vehicle> getAllVehiclesByRefOrgans(@RequestHeader("authorization") String authorization) {
//        LOGGER.info("REST request to get all Vehicles");
//        return vehicleService.findAllByRefOrgans(authorization,getUserProfil().get());
//    }

    @GetMapping("/vehicules/{id}")
    public ResponseEntity<Vehicle> getVehicle(@RequestHeader Map<String, String> headers, @PathVariable String id) {
        LOGGER.info("REST request to get Vehicle : {}", id);
        Optional<Vehicle> vehicle = null;
        if (userHasAuthority("ADMIN") && id != null) {
            vehicle = vehicleService.findOne(null, id);
            LOGGER.info("REST request to get Vehicle :by", headers);
//            return new ResponseEntity<>(vehicle.get(), OK);
        } else if (id != null) {
            for (String authoritUser : getUserProfil().get().getAuthorities()) {
                if (authoritUser.equals(vehicle.get().getPrefOrgan())) {
                     vehicle = vehicleService.findOne(null, id);
                    LOGGER.info("REST request to get Vehicle :by", headers);
//                    return new ResponseEntity<>(vehicle.get(), OK);
                }
            }
        } else {
            LOGGER.info("REST request to get Vehicle null :by", headers);
            vehicle = null;
        }

        return new ResponseEntity<>(vehicle.get(), OK);
    }


    @DeleteMapping("/vehicules/{id}")
    public ResponseEntity<HttpResponse> deleteVehicle(@PathVariable String id, @RequestHeader Map<String, String> headers) {
        LOGGER.info("REST request to delete Vehicle : {}", id);
        if (userHasAuthority("ADMIN") && id != null) {
            vehicleService.delete(id);
            return response(OK, "Vehicule_DELETED_SUCCESSFULLY" + headers);
        } else {
            Optional<Vehicle> vehicle = vehicleService.findOne(null, id);
            for (String authoritUser : getUserProfil().get().getAuthorities()) {
                if (authoritUser.equals(vehicle.get().getPrefOrgan())) {
                    vehicleService.delete(id);

                }
            }
            return response(OK, "Vehicule_DELETED_SUCCESSFULLY by " + headers);
        }
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }
   
}
