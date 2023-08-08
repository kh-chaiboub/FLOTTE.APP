package com.rh.vehicle.repository;

import com.rh.vehicle.domain.Fuel;
import com.rh.vehicle.domain.Mission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Mission entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MissionRepository extends MongoRepository<Mission, String> {
}
