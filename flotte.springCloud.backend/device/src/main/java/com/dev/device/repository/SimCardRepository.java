package com.dev.device.repository;

import com.dev.device.domain.SimCard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimCardRepository extends MongoRepository<SimCard, String> {
}
