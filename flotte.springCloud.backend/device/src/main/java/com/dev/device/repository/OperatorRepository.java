package com.dev.device.repository;

import com.dev.device.domain.Operator;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatorRepository extends MongoRepository<Operator, String> {

}