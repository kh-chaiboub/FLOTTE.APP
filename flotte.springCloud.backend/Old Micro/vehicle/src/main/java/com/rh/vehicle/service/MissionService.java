package com.rh.vehicle.service;

import com.rh.vehicle.domain.Mission;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Mission}.
 */
public interface MissionService {
    /**
     * Save a mission.
     *
     * @param mission the entity to save.
     * @return the persisted entity.
     */
    Mission save(Mission mission);

    /**
     * Partially updates a mission.
     *
     * @param mission the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Mission> partialUpdate(Mission mission);

    /**
     * Get all the missions.
     *
     * @return the list of entities.
     */
    List<Mission> findAll();

    /**
     * Get the "id" mission.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Mission> findOne(String id);

    /**
     * Delete the "id" mission.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
