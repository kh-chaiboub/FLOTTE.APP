package com.rh.vehicle.service.impl;

import com.rh.vehicle.domain.EtatVehicule;
import com.rh.vehicle.repository.EtatVehiculeRepository;
import com.rh.vehicle.service.EtatVehiculeService;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link EtatVehicule}.
 */
@Service
public class EtatVehiculeServiceImpl implements EtatVehiculeService {

    private final Logger log = LoggerFactory.getLogger(EtatVehiculeServiceImpl.class);

    private final EtatVehiculeRepository etatVehiculeRepository;

    public EtatVehiculeServiceImpl(EtatVehiculeRepository etatVehiculeRepository) {
        this.etatVehiculeRepository = etatVehiculeRepository;
    }

    @Override
    public EtatVehicule save(EtatVehicule etatVehicule) {
        log.debug("Request to save EtatVehicule : {}", etatVehicule);
        return etatVehiculeRepository.save(etatVehicule);
    }

    @Override
    public Optional<EtatVehicule> partialUpdate(EtatVehicule etatVehicule) {
        log.debug("Request to partially update EtatVehicule : {}", etatVehicule);

        return etatVehiculeRepository
                .findById(etatVehicule.getId())
                .map(existingEtatVehicule -> {
                    if (etatVehicule.getVehicleStatus() != null) {
                        existingEtatVehicule.setVehicleStatus(etatVehicule.getVehicleStatus());
                    }

                    return existingEtatVehicule;
                })
                .map(etatVehiculeRepository::save);
    }

    @Override
    public List<EtatVehicule> findAll() {
        log.debug("Request to get all EtatVehicules");
        return etatVehiculeRepository.findAll();
    }

    @Override
    public Optional<EtatVehicule> findOne(String id) {
        log.debug("Request to get EtatVehicule : {}", id);
        return etatVehiculeRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete EtatVehicule : {}", id);
        etatVehiculeRepository.deleteById(id);
    }
}
