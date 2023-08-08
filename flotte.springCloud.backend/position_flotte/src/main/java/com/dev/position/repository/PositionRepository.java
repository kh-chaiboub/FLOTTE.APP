package com.dev.position.repository;

import com.dev.position.domain.Position;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PositionRepository extends MongoRepository<Position, String> {
    List<Position> findByDeviceIDAndDeviceTimeBetween(String deviceid,Date dateD,Date DateF);
}
