package com.rh.vehicle.repository;

import com.rh.vehicle.domain.Vehicle;

import com.rh.vehicle.model.Device;
import com.rh.vehicle.service.VehiculeProjectionListMap;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.Projection;
import org.springframework.stereotype.Repository;
//import reactor.core.publisher.Flux;

import java.util.Date;
import java.util.List;


@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String> {

    List<Vehicle> findByPrefOrganIn(List<String> prefs);

    @Aggregation(pipeline = {
                "{$match :{\"device\":null}}",
            " {$count :  'DeviceByStatus'}"
    })
    Integer  vehiculeNotDote();

    @Aggregation(pipeline = {
            "{$match :{ \"prefOrgan\": {$in: ?0},\"device\":null}}",
            " {$count :  'DeviceByStatus'}"
    })
    Integer vehiculeNotDoteByRefOrgan(List<String> prefs);


    List<Vehicle> findByDeviceStatusFalse();
    int  countByDeviceStatusFalse();

    List<Vehicle> findByDeviceStatusTrue();
    int  countByDeviceStatusTrue();

    @Query(value = "{}", fields = "{ 'registrationNumber' : 1, 'refOrgan' : 1,'prefOrgan' : 1, 'device' : 1, 'lastPosition' : 1, 'vehiculemaxvitesse' : 1,'vehiclemodel' : 1 }")
    List<VehiculeProjectionListMap> findAllByVehicle(Pageable pageable);

    @Query(value = "{'device.status': true }", fields = "{ 'registrationNumber' : 1, 'refOrgan' : 1,'prefOrgan' : 1, 'device' : 1, 'lastPosition' : 1, 'vehiculemaxvitesse' : 1,'vehiclemodel' : 1 }")
    List<VehiculeProjectionListMap> findByDeviceStatusTrue(Pageable pageable);

    @Query(value = "{'device.status': false }", fields = "{ 'registrationNumber' : 1, 'refOrgan' : 1,'prefOrgan' : 1, 'device' : 1, 'lastPosition' : 1, 'vehiculemaxvitesse' : 1,'vehiclemodel' : 1 }")
    List<VehiculeProjectionListMap> findByDeviceStatusFalse(Pageable pageable);


    List<VehiculeProjectionListMap> findByRegistrationNumberContains(String imm,Pageable pageable);


}
