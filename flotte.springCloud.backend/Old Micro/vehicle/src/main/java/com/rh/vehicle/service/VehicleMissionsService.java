package com.rh.vehicle.service;

import com.rh.vehicle.domain.VehiculeMission;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link VehiculeMission}.
 */
public interface VehicleMissionsService {
    /**
     * Save a vehiculeMission.
     *s
     * @param vehiculeMission the entity to save.
     * @return the persisted entity.
     */
    VehiculeMission save(VehiculeMission vehiculeMission);

    /**
     * Partially updates a vehiculeMission.
     *
     * @param vehiculeMission the entity to update partially.
     * @return the persisted entity.
     */
    Optional<VehiculeMission> partialUpdate(VehiculeMission vehiculeMission);

    /**
     * Get all the vehiculeMission.
     *
     * @return the list of entities.
     */
    List<VehiculeMission> findAll();

    /**
     * Get the "id" vehiculeMission.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VehiculeMission> findOne(String id);

    /**
     * Delete the "id" vehiculeMission.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
