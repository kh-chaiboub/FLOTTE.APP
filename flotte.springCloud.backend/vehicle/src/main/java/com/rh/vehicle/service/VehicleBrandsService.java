package com.rh.vehicle.service;

import com.rh.vehicle.domain.VehicleBrands;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link VehicleBrands}.
 */
public interface VehicleBrandsService {
    /**
     * Save a vehicleBrands.
     *
     * @param vehicleBrands the entity to save.
     * @return the persisted entity.
     */
    VehicleBrands save(VehicleBrands vehicleBrands);

    /**
     * Partially updates a vehicleBrands.
     *
     * @param vehicleBrands the entity to update partially.
     * @return the persisted entity.
     */
    Optional<VehicleBrands> partialUpdate(VehicleBrands vehicleBrands);

    /**
     * Get all the vehicleBrands.
     *
     * @return the list of entities.
     */
    List<VehicleBrands> findAll();

    /**
     * Get the "id" vehicleBrands.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VehicleBrands> findOne(String id);

    /**
     * Get the "id" vehicleBrands.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    List<VehicleBrands> findBrandsByType(String id);


    /**
     * Delete the "id" vehicleBrands.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
