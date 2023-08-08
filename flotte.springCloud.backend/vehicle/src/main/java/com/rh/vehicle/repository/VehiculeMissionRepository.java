package com.rh.vehicle.repository;

import com.rh.vehicle.domain.VehiculeMission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculeMissionRepository extends MongoRepository<VehiculeMission,String> {
}
