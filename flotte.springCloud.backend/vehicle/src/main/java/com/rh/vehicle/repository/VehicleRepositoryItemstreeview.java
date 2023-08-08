package com.rh.vehicle.repository;

import com.rh.vehicle.domain.Vehicle;
import com.rh.vehicle.domain.VehicleNew;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data MongoDB repository for the Vehicle entity.
 */

@Repository
public interface VehicleRepositoryItemstreeview extends MongoRepository<VehicleNew, String> {

}
