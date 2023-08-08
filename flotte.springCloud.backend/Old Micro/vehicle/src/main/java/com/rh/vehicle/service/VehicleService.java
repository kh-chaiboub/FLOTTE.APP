package com.rh.vehicle.service;

import com.rh.vehicle.domain.Vehicle;
import com.rh.vehicle.domain.VehicleNew;
import com.rh.vehicle.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.config.Projection;
//import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Vehicle}.
 */
public interface VehicleService {
    /**
     * Save a vehicle.
     *
     * @param vehicle the entity to save.
     * @return the persisted entity.
     */
    Vehicle save(Vehicle vehicle);

    /**
     * Partially updates a vehicle.
     *
     * @param vehicle the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Vehicle> partialUpdate(Vehicle vehicle);

    /**
     * Get all the vehicles.
     *
     * @return the list of entities.
     */
    List<Vehicle> findAll(String authorization);
   List<VehiculeProjectionListMap> findAllProjection(Pageable pageable, String authorization);
    List<VehiculeProjectionListMap> findByRegistrationNumberContains(String imm,Pageable pageable, String authorization);
    List<VehicleNew> findAllItemstreeview(String authorization);
//    int countVehicleByDevice(boolean b);


    /**
     * Get all the vehicles by RefOrgan.
     *
     * @return the list of entities.
     */
    List<Vehicle> findAllByRefOrgans(String authorization, User userProfil);

    /**
     * Get the "id" vehicle.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Vehicle> findOne(String authorization,String id);


    /**
     * Delete the "id" vehicle.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    /**
     * Get all the vehicles.
     *
     * @return the list of entities.
     */
//    List<Vehicle> ListVehicleByNotDoteDevice();

    List<Vehicle> findByDeviceStatusFalse();

    int countByDeviceStatusFalse();

    List<Vehicle> findByDeviceStatusTrue();

    int countByDeviceStatusTrue();

//    int countVehiclesWithDeviceTimeAfter24Hours();

    int countVehicleByNotDoteDevice();
    int countVehicleByNotDoteDeviceByRefOrgan(User user);

//    int countVehicleByDeviceByRefOrgan(User user, boolean b);


    List<VehiculeProjectionListMap> findAllProjectionStatusTrue(Pageable secondPageWithFiveElements, Object o);
    List<VehiculeProjectionListMap> findAllProjectionStatusFalse(Pageable secondPageWithFiveElements, Object o);

}
