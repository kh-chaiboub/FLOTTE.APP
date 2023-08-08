package com.rh.vehicle.service.impl;

import com.rh.vehicle.domain.VehicleBrands;
import com.rh.vehicle.domain.VehiculeTypes;
import com.rh.vehicle.repository.VehicleBrandsRepository;
import com.rh.vehicle.repository.VehicleTypesRepository;
import com.rh.vehicle.service.VehicleBrandsService;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link VehicleBrands}.
 */
@Service
public class VehicleBrandsServiceImpl implements VehicleBrandsService {

    private final Logger log = LoggerFactory.getLogger(VehicleBrandsServiceImpl.class);

    private final VehicleBrandsRepository vehicleBrandsRepository;
    private  final VehicleTypesRepository vehicleTypesRepository;

    public VehicleBrandsServiceImpl(VehicleBrandsRepository vehicleBrandsRepository, VehicleTypesRepository vehicleTypesRepository) {
        this.vehicleBrandsRepository = vehicleBrandsRepository;
        this.vehicleTypesRepository = vehicleTypesRepository;
    }

    @Override
    public VehicleBrands save(VehicleBrands vehicleBrands) {
        log.debug("Request to save VehicleBrands : {}", vehicleBrands);
        return vehicleBrandsRepository.save(vehicleBrands);
    }

    @Override
    public Optional<VehicleBrands> partialUpdate(VehicleBrands vehicleBrands) {
        log.debug("Request to partially update VehicleBrands : {}", vehicleBrands);

        return vehicleBrandsRepository
                .findById(vehicleBrands.getId())
                .map(existingVehicleBrands -> {
                    if (vehicleBrands.getVehicleBrand() != null) {
                        existingVehicleBrands.setVehicleBrand(vehicleBrands.getVehicleBrand());
                    }

                    return existingVehicleBrands;
                })
                .map(vehicleBrandsRepository::save);
    }

    @Override
    public List<VehicleBrands> findAll() {
        log.debug("Request to get all VehicleBrands");
        return vehicleBrandsRepository.findAll();
    }

    @Override
    public Optional<VehicleBrands> findOne(String id) {
        log.debug("Request to get VehicleBrands : {}", id);
        return vehicleBrandsRepository.findById(id);
    }

    @Override
    public List<VehicleBrands> findBrandsByType(String id) {

        Optional<VehiculeTypes> vehiculeType = vehicleTypesRepository.findById(id);

        List<VehicleBrands> vehicleBrands=vehicleBrandsRepository.findByVehiculeType(vehiculeType);

        return vehicleBrands;

    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete VehicleBrands : {}", id);
        vehicleBrandsRepository.deleteById(id);
    }
}
