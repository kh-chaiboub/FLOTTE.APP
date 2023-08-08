package com.dev.device.service.impl;

import com.dev.device.domain.Brand;
import com.dev.device.domain.BrandCount;
import com.dev.device.domain.Device;
import com.dev.device.model.*;
import com.dev.device.repository.BrandRepository;
import com.dev.device.repository.DeviceRepository;
import com.dev.device.service.DeviceProjectionListMap;
import com.dev.device.service.DeviceService;
import com.rh.vehicle.service.VehiculeProjectionListMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Service Implementation for managing {@link Device}.
 */
@Service
@Transactional
public class DeviceServiceImpl implements DeviceService {

    private final Logger log = LoggerFactory.getLogger(DeviceServiceImpl.class);
    @Autowired
    private final DeviceRepository deviceRepository;
    @Autowired
    private LastPositionClient lastPositionClient;

    @Autowired
    private final BrandRepository brandRepository;



    public DeviceServiceImpl(DeviceRepository deviceRepository, LastPositionClient lastPositionClient, BrandRepository brandRepository) {
        this.deviceRepository = deviceRepository;
        this.lastPositionClient = lastPositionClient;


        this.brandRepository = brandRepository;
    }

    @Override
    public Device save(Device device) {

        log.debug("REST request to save Device : {}", device);
        if (device.getId() != null) {
            //throw new BadRequestAlertException("A new device cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Device result = deviceRepository.save(device);
        return result;

    }

    @Override
    public Optional<Device> partialUpdate(Device device) {

        return deviceRepository
                .findById(device.getId())
                .map(
                        existingDevice -> {
                            if (device.getDeviceID() != null) {
                                existingDevice.setDeviceID(device.getDeviceID());
                            }
                            if (device.getSerialNumber() != null) {
                                existingDevice.setSerialNumber(device.getSerialNumber());
                            }
                            if (device.getSimserialNumber()!= null) {
                                existingDevice.setSimserialNumber(device.getSimserialNumber());
                            }
                            if (device.getImei() != null) {
                                existingDevice.setImei(device.getImei());
                            }
                            if (device.getActive() != null) {
                                existingDevice.setActive(device.getActive());
                            }

                            if (device.getState() != null) {
                                existingDevice.setState(device.getState());
                            }

                            if (device.getLastUpdate() != null) {
                                existingDevice.setLastUpdate(device.getLastUpdate());
                            }
                            if (device.getPhoneNumber() != null) {
                                existingDevice.setPhoneNumber(device.getPhoneNumber());
                            }
                            if (device.getDevicemodel() != null) {
                                existingDevice.setDevicemodel(device.getDevicemodel());
                            }
                            if (device.getRefOrgan() != null) {
                                existingDevice.setRefOrgan(device.getRefOrgan());
                            }


                            return existingDevice;
                        }

                )
                .map(deviceRepository::save);
    }


    @Override
    public List<Device> findAll() {
        log.debug("Request to get all Brands");
        return deviceRepository.findAll();
    }

    @Override
    public Page<Device> findAllWithEagerRelationships(Pageable pageable) {

        return deviceRepository.findAll(pageable);
    }
    @Override
    public List<DeviceProjectionListMap> findAllProjection(Pageable pageable, String authorization) {
        return deviceRepository.findAllByDevice(pageable);
    }

    @Override
    public Optional<Device> findOne(String id) {
        return deviceRepository.findById(id);
    }
    @Override
    public Optional<Device> findOneByrefOrgan(String id,User userProfil) {
        List<String> listRef = Arrays.asList(userProfil.getAuthorities());
        Optional<Device> device=deviceRepository.findByIdAndPrefOrganIn(id,listRef);
        if(device!=null && device.isPresent()){
            LastPosition  lastPosition =lastPositionClient.getLastPositionByDeviceID(device.get().getImei());//
            device.get().setLastPosition(lastPosition);
        }

        return device;
    }

    @Override
    public Optional<Device> findByDeviceID(String deviceId) {
        Optional<Device> device=deviceRepository.findByDeviceID(deviceId);
        return device;

    }
    @Override
    public Optional<Device> findByDeviceIDByrefOrgan(String deviceId,User userProfil) {

        List<String> listRef = Arrays.asList(userProfil.getAuthorities());

        Optional<Device> device=deviceRepository.findByDeviceIDAndPrefOrganIn(deviceId,listRef);

        return device;

    }

    @Override
    public Optional<Device> findByImei(String imei) throws RuntimeException{
        Optional<Device> device=deviceRepository.findByImei(imei);

        if(device!=null && device.isPresent()) {
            return device;
        }else{
            return null;
        }

    }
    @Override
    public Optional<Device> findByImeiByrefOrgan(String imei,User userProfil) {
        List<String> listRef = Arrays.asList(userProfil.getAuthorities());
        Optional<Device> device=deviceRepository.findByImeiAndPrefOrganIn(imei,listRef);
        return device;
    }

    @Override
    public void delete(String id) {
        deviceRepository.deleteById(id);

    }

    @Override
    public List<Device> findAllByRefOrgans(User userProfil) {

        List<String> listRef = Arrays.asList(userProfil.getAuthorities());
        List<Device> devices =deviceRepository.findByPrefOrganIn(listRef);
        return  devices;

    }

    @Override
    public  List<Brand> TotalDeviceByBrand() {
       
        List<Brand> listDeviceBrand = new ArrayList();

        List<Brand> brandList= brandRepository.findAll();

        for(Brand brand:brandList){


            listDeviceBrand.add(brand);

        }

         return listDeviceBrand;
    }

    @Override
    public List<BrandCount> countDevicesByBrand() {
       return deviceRepository.countDevicesByBrand().getMappedResults();
    }


}
