package com.rh.vehicle.service.impl;

import com.rh.vehicle.domain.VehiculeTypes;
import com.rh.vehicle.repository.VehicleTypesRepository;
import com.rh.vehicle.service.VehicleTypesService;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link VehiculeTypes}.
 */
@Service
public class VehicleTypesServiceImpl implements VehicleTypesService {

    private final Logger log = LoggerFactory.getLogger(VehicleTypesServiceImpl.class);

    private final VehicleTypesRepository vehicleTypesRepository;

    public VehicleTypesServiceImpl(VehicleTypesRepository vehicleTypesRepository) {
        this.vehicleTypesRepository = vehicleTypesRepository;
    }

    @Override
    public VehiculeTypes save(VehiculeTypes vehicleTypes) {
        log.debug("Request to save VehicleTypes : {}", vehicleTypes);
        return vehicleTypesRepository.save(vehicleTypes);
    }

    @Override
    public Optional<VehiculeTypes> partialUpdate(VehiculeTypes vehicleTypes) {
        log.debug("Request to partially update VehicleTypes : {}", vehicleTypes);

        return vehicleTypesRepository
                .findById(vehicleTypes.getId())
                .map(existingVehicleTypes -> {
                    if (vehicleTypes.getVehiculeType() != null) {
                        existingVehicleTypes.setVehiculeType(vehicleTypes.getVehiculeType());
                    }

                    return existingVehicleTypes;
                })
                .map(vehicleTypesRepository::save);
    }

    @Override
    public List<VehiculeTypes> findAll() {
        log.debug("Request to get all VehicleTypes");
        return vehicleTypesRepository.findAll();
    }

    @Override
    public Optional<VehiculeTypes> findOne(String id) {
        log.debug("Request to get VehicleTypes : {}", id);
        return vehicleTypesRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete VehicleTypes : {}", id);
        vehicleTypesRepository.deleteById(id);
    }
}
