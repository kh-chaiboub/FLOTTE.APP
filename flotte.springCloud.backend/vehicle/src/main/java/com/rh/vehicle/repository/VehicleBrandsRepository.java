package com.rh.vehicle.repository;

import com.rh.vehicle.domain.VehicleBrands;
import com.rh.vehicle.domain.VehiculeTypes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data MongoDB repository for the VehicleBrands entity.
 */
@Repository
public interface VehicleBrandsRepository extends MongoRepository<VehicleBrands, String> {
    public List<VehicleBrands> findByVehiculeType(Optional<VehiculeTypes> vehiculeType);

}
