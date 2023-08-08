package com.rh.vehicle.service;

import com.rh.vehicle.domain.EtatVehicule;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link EtatVehicule}.
 */
public interface EtatVehiculeService {
    /**
     * Save a etatVehicule.
     *
     * @param etatVehicule the entity to save.
     * @return the persisted entity.
     */
    EtatVehicule save(EtatVehicule etatVehicule);

    /**
     * Partially updates a etatVehicule.
     *
     * @param etatVehicule the entity to update partially.
     * @return the persisted entity.
     */
    Optional<EtatVehicule> partialUpdate(EtatVehicule etatVehicule);

    /**
     * Get all the etatVehicules.
     *
     * @return the list of entities.
     */
    List<EtatVehicule> findAll();

    /**
     * Get the "id" etatVehicule.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EtatVehicule> findOne(String id);

    /**
     * Delete the "id" etatVehicule.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
