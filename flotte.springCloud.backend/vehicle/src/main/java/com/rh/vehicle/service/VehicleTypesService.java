package com.rh.vehicle.service;

import com.rh.vehicle.domain.VehiculeTypes;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link VehiculeTypes}.
 */
public interface VehicleTypesService {
    /**
     * Save a vehicleTypes.
     *
     * @param vehicleTypes the entity to save.
     * @return the persisted entity.
     */
    VehiculeTypes save(VehiculeTypes vehicleTypes);

    /**
     * Partially updates a vehicleTypes.
     *
     * @param vehicleTypes the entity to update partially.
     * @return the persisted entity.
     */
    Optional<VehiculeTypes> partialUpdate(VehiculeTypes vehicleTypes);

    /**
     * Get all the vehicleTypes.
     *
     * @return the list of entities.
     */
    List<VehiculeTypes> findAll();

    /**
     * Get the "id" vehicleTypes.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VehiculeTypes> findOne(String id);

    /**
     * Delete the "id" vehicleTypes.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
