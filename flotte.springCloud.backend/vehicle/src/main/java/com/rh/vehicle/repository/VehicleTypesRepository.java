package com.rh.vehicle.repository;

import com.rh.vehicle.domain.VehiculeTypes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the VehicleTypes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VehicleTypesRepository extends MongoRepository<VehiculeTypes, String> {
}
