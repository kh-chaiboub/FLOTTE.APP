package com.rh.vehicle.service;

import com.rh.vehicle.domain.VehicleModel;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link VehicleModel}.
 */
public interface VehicleModelService {
    /**
     * Save a vehicleModel.
     *
     * @param vehicleModel the entity to save.
     * @return the persisted entity.
     */
    VehicleModel save(VehicleModel vehicleModel);

    /**
     * Partially updates a vehicleModel.
     *
     * @param vehicleModel the entity to update partially.
     * @return the persisted entity.
     */
    Optional<VehicleModel> partialUpdate(VehicleModel vehicleModel);

    /**
     * Get all the vehicleModels.
     *
     * @return the list of entities.
     */
    List<VehicleModel> findAll();

    /**
     * Get the "id" vehicleModel.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VehicleModel> findOne(String id);

    List<VehicleModel> getAllModelsByBrands(String id);

    /**
     * Delete the "id" vehicleModel.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
