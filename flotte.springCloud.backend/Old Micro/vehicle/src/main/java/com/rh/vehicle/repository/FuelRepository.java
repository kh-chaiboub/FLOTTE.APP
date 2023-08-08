package com.rh.vehicle.repository;

import com.rh.vehicle.domain.Fuel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Fule entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FuelRepository extends MongoRepository<Fuel, String> {
}
