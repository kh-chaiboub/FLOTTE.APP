package com.rh.vehicle.service.impl;

import com.rh.vehicle.domain.Fuel;
import com.rh.vehicle.repository.FuelRepository;
import com.rh.vehicle.service.FuelService;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class FuelServiceImpl implements FuelService {

    private final Logger log = LoggerFactory.getLogger(FuelServiceImpl.class);

    private final FuelRepository fuelRepository;

    public FuelServiceImpl(FuelRepository fuelRepository) {
        this.fuelRepository = fuelRepository;
    }

    @Override
    public Fuel save(Fuel fuel) {
        log.debug("Request to save Fuel : {}", fuel);
        return fuelRepository.save(fuel);
    }

    @Override
    public Optional<Fuel> partialUpdate(Fuel fuel) {
        log.debug("Request to partially update Fuel : {}", fuel);

        return fuelRepository
                .findById(fuel.getId())
                .map(existingFule -> {
                    if (fuel.getFuel() != null) {
                        existingFule.setFuel(fuel.getFuel());
                    }

                    return existingFule;
                })
                .map(fuelRepository::save);
    }

    @Override
    public List<Fuel> findAll() {
        log.debug("Request to get all Fuels");
        return fuelRepository.findAll();
    }

    @Override
    public Optional<Fuel> findOne(String id) {
        log.debug("Request to get Fuel : {}", id);
        return fuelRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Fuel : {}", id);
        fuelRepository.deleteById(id);
    }
}
