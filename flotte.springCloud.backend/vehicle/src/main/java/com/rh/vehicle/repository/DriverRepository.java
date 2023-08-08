package com.rh.vehicle.repository;


import com.rh.vehicle.domain.Driver;
import com.rh.vehicle.service.DriverProjectionListMap;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository  extends MongoRepository<Driver,String> {
    Optional<Driver>  getDriverByNbrmilitaire(String nbm);
    Optional<Driver>  getDriverByNpermis(String npermis);
    Optional<Driver> getDriverByMle(int mle);
    @Query(value = "{}", fields = "{ 'npermis' : 1, 'categoryPermis' : 1,'person' : 1}")
    List<DriverProjectionListMap> findAllByDriver(Pageable pageable);
}
