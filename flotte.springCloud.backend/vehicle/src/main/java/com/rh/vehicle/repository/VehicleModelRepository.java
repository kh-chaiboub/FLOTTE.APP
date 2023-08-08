package com.rh.vehicle.repository;

import com.rh.vehicle.domain.VehicleBrands;
import com.rh.vehicle.domain.VehicleModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data MongoDB repository for the VehicleModel entity.
 */
@SuppressWarnings("unused")
@Repository

public interface VehicleModelRepository extends MongoRepository<VehicleModel, String> {
    public List<VehicleModel> findByVehicleBrands (Optional<VehicleBrands> vehicleBrandsOptional);
}
