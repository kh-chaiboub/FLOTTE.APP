package com.rh.vehicle.repository;

import com.rh.vehicle.domain.EtatVehicule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the EtatVehicule entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EtatVehiculeRepository extends MongoRepository<EtatVehicule, String> {
}
