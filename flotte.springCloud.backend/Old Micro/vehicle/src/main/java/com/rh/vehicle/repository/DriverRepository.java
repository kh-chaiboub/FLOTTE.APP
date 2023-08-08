package com.rh.vehicle.repository;


import com.rh.vehicle.domain.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository  extends MongoRepository<Driver,String> {
    Optional<Driver>  getDriverByNbrmilitaire(String nbm);
    Optional<Driver>  getDriverByNpermis(String npermis);
    Optional<Driver> getDriverByMle(int mle);
}
