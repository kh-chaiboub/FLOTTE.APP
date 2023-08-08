package com.dev.position.repository;

import com.dev.position.domain.LastPosition;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LastPositionRepository extends MongoRepository<LastPosition, String> {
    public Optional<LastPosition> findByDeviceID(String deviceId);
}
