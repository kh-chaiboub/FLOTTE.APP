package com.dev.device.repository;
import com.dev.device.domain.BrandCount;
import com.dev.device.domain.Device;
import com.dev.device.service.DeviceProjectionListMap;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface DeviceRepository extends MongoRepository<Device, String> {
    List<Device> findByPrefOrganIn(List<String> ref);
    Optional<Device> findByIdAndPrefOrganIn(String id, List<String> listRef);


    public Optional<Device> findByDeviceID(String deviceID);
    public Optional<Device> findByDeviceIDAndPrefOrganIn(String deviceID,List<String> ref );


    public Optional<Device> findByImei(String imei);
    Optional<Device> findByImeiAndPrefOrganIn(String imei, List<String> listRef);


    @Aggregation({
            "{ $unwind : '$devicemodel'}",
          " { $lookup : { from : 'model', localField : 'devicemodel.$id', foreignField : '_id', 'as' : 'modelBrand'}}",
            "{ $unwind : '$modelBrand'}",
            "{ $lookup : { from : 'brand', localField : 'modelBrand.brand.$id', foreignField : '_id', as : 'brands'}}",
            " { $unwind : '$brands'}",
            "{$group: {_id: '$_id',brand : {$push : '$brands'}}}",
            "{ $group : {_id: { brand : '$brand.deviceBrand'}, totalBalise : { $sum : 1}}}",
            "{$match:{'_id.brand':?0}}",
    })

public Integer TotalDeviceBrand(String str);

    @Aggregation({
            "{$group: {_id: '$devicemodel.brand.deviceBrand',count: { $sum: 1 } }}",

    })
    AggregationResults<BrandCount> countDevicesByBrand();

    @Query(value = "{}", fields = "{ 'deviceID' : 1, 'serialNumber' : 1, 'refOrgan' : 1, 'prefOrgan' : 1,'imei' : 1, 'active' : 1, 'state' : 1, 'status' : 1,'phoneNumber' : 1 }")
    List<DeviceProjectionListMap> findAllByDevice(Pageable pageable);
}
