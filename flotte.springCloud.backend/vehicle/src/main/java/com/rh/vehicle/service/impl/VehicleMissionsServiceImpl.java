package com.rh.vehicle.service.impl;

import com.rh.vehicle.domain.VehiculeMission;
import com.rh.vehicle.repository.VehiculeMissionRepository;
import com.rh.vehicle.service.VehicleMissionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleMissionsServiceImpl implements VehicleMissionsService {

    private final Logger log = LoggerFactory.getLogger(VehicleMissionsServiceImpl.class);
    private final VehiculeMissionRepository vehiculeMissionRepository;

    public VehicleMissionsServiceImpl(VehiculeMissionRepository vehiculeMissionRepository) {
        this.vehiculeMissionRepository = vehiculeMissionRepository;
    }


    @Override
    public VehiculeMission save(VehiculeMission vehiculeMission) {
        log.debug("Request to save vehiculeMission : {}", vehiculeMission);
        return vehiculeMissionRepository.save(vehiculeMission);
    }

    @Override
    public Optional<VehiculeMission> partialUpdate(VehiculeMission vehiculeMission) {
        log.debug("Request to partially update VehiculeMission : {}", vehiculeMission);

        return vehiculeMissionRepository
                .findById(vehiculeMission.getId())
                .map(existingVehicleMissions -> {
                    if (vehiculeMission.getMissionVehiculeName() != null) {
                        existingVehicleMissions.setMissionVehiculeName(vehiculeMission.getMissionVehiculeName());
                    }
                    if (vehiculeMission.getMission_DateD() != null) {
                        existingVehicleMissions.setMission_DateD(vehiculeMission.getMission_DateD());
                    }
                    if (vehiculeMission.getMission_DateF() != null) {
                        existingVehicleMissions.setMission_DateF(vehiculeMission.getMission_DateF());
                    }
                    return existingVehicleMissions;
                })
                .map(vehiculeMissionRepository::save);
    }

    @Override
    public List<VehiculeMission> findAll() {

        log.debug("Request to get all VehiculeMission");
        return vehiculeMissionRepository.findAll();
    }

    @Override
    public Optional<VehiculeMission> findOne(String id) {
        log.debug("Request to get VehiculeMission : {}", id);
        return vehiculeMissionRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete VehicleTypes : {}", id);
        vehiculeMissionRepository.deleteById(id);
    }
}
