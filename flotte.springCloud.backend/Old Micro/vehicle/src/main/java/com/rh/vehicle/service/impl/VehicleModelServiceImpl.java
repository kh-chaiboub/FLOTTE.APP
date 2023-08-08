package com.rh.vehicle.service.impl;

import com.rh.vehicle.domain.VehicleBrands;
import com.rh.vehicle.domain.VehicleModel;
import com.rh.vehicle.repository.VehicleModelRepository;
import com.rh.vehicle.repository.VehicleTypesRepository;
import com.rh.vehicle.service.VehicleBrandsService;
import com.rh.vehicle.service.VehicleModelService;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link VehicleModel}.
 */
@Service
public class VehicleModelServiceImpl implements VehicleModelService {

    private final Logger log = LoggerFactory.getLogger(VehicleModelServiceImpl.class);

    private final VehicleModelRepository vehicleModelRepository;

    private  final VehicleBrandsService vehicleBrandsService;

    public VehicleModelServiceImpl(VehicleModelRepository vehicleModelRepository, VehicleBrandsService vehicleBrandsService) {
        this.vehicleModelRepository = vehicleModelRepository;
        this.vehicleBrandsService = vehicleBrandsService;
    }

    @Override
    public VehicleModel save(VehicleModel vehicleModel) {
        log.debug("Request to save VehicleModel : {}", vehicleModel);
        return vehicleModelRepository.save(vehicleModel);
    }

    @Override
    public Optional<VehicleModel> partialUpdate(VehicleModel vehicleModel) {
        log.debug("Request to partially update VehicleModel : {}", vehicleModel);

        return vehicleModelRepository
                .findById(vehicleModel.getId())
                .map(existingVehicleModel -> {
                    if (vehicleModel.getModelName() != null) {
                        existingVehicleModel.setModelName(vehicleModel.getModelName());
                    }

                    return existingVehicleModel;
                })
                .map(vehicleModelRepository::save);
    }

    @Override
    public List<VehicleModel> findAll() {
        log.debug("Request to get all VehicleModels");
        return vehicleModelRepository.findAll();
    }

    @Override
    public Optional<VehicleModel> findOne(String id) {
        log.debug("Request to get VehicleModel : {}", id);
        return vehicleModelRepository.findById(id);
    }

    @Override
    public List<VehicleModel> getAllModelsByBrands(String id) {
        log.debug("Request to get VehicleModel : {}", id);
        Optional<VehicleBrands> vehicleBrand = vehicleBrandsService.findOne(id);
        return vehicleModelRepository.findByVehicleBrands(vehicleBrand);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete VehicleModel : {}", id);
        vehicleModelRepository.deleteById(id);
    }
}
