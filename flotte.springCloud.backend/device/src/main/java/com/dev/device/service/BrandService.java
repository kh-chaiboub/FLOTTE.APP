package com.dev.device.service;


import com.dev.device.domain.Brand;
import com.dev.device.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BrandService {


    /**
     * Save a Brand.
     *
     * @param brand the entity to save.
     * @return the persisted entity.
     */

    Brand save(Brand brand);

    /**
     * Partially updates a brand.
     *
     * @param brand the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Brand> partialUpdate(Brand brand);

    /**
     * Get all the brands.
     *
     * @return the list of entities.
     */
    List<Brand> findAll();


    /**
     * Get all the brands.
     *
     * @return the list of entities.
     */
    List<Brand> findAllByCategory(Optional<Category> category);

    /**
     * Get all the brands with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Brand> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" brand.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Brand> findOne(String id);


//        /**
//         * Get the "brandId" brand.
//         *
//         * @param deviceBrand the id of the entity.
//         * @return the entity.
//         */
//        Optional<Brand> findByDeviceBrand(String deviceBrand);

    /**
     * Delete the "id" brand.
     *
     * @param id the id of the entity.
     */
    void delete(String id);


}
