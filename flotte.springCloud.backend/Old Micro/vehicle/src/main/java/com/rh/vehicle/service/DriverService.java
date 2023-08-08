package com.rh.vehicle.service;



import com.rh.vehicle.domain.Driver;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Driver}.
 */
public interface DriverService {
    /**
     * Save a driver.
     *
     * @param driver the entity to save.
     * @return the persisted entity.
     */
    Driver save(Driver driver);

    /**
     * Partially updates a driver.
     *
     * @param driver the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Driver> partialUpdate(Driver driver);

    /**
     * Get all the drivers.
     *
     * @return the list of entities.
     */
    List<Driver> findAll();

    /**
     * Get the "id" driver.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Driver> findOne(String id);


    /**
     * Get the "mle" driver.
     *
     * @param mle the id of the entity.
     * @return the entity.
     */
    Optional<Driver> findByMle(int mle);


    /**
     * Delete the "id" vehicle.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
