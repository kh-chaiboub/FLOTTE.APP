package com.dev.device.service;

import com.dev.device.domain.Brand;
import com.dev.device.domain.BrandCount;
import com.dev.device.domain.Device;
import com.dev.device.model.User;
import com.rh.vehicle.service.VehiculeProjectionListMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DeviceService {

    /**
     * Save a device.
     *
     * @param device the entity to save.
     * @return the persisted entity.
     */

    Device save(Device device);

    /**
     * Partially updates a device.
     *
     * @param device the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Device> partialUpdate(Device device);

    /**
     * Get all the devices.
     *
     * @return the list of entities.
     */
    List<Device> findAll();
    List<DeviceProjectionListMap> findAllProjection(Pageable pageable, String authorization);

    /**
     * Get all the devices with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Device> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" device.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Device> findOne(String id);


    Optional<Device> findOneByrefOrgan(String id,User userProfil);

    /**
     * Get the "deviceId" device.
     *
     * @param deviceId the id of the entity.
     * @return the entity.
     */
    Optional<Device> findByDeviceID(String deviceId);
    Optional<Device> findByDeviceIDByrefOrgan(String deviceId,User userProfil);


    Optional<Device> findByImei(String imei);
    Optional<Device> findByImeiByrefOrgan(String imei,User userProfil);

    /**
     * Delete the "id" device.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    /**
     * Get all the vehicles by RefOrgan.
     *
     * @return the list of entities.
     */
    List<Device> findAllByRefOrgans(User userProfil);

    List<Brand> TotalDeviceByBrand();

    List<BrandCount> countDevicesByBrand();


}
