package com.dev.device.service;

import com.dev.device.domain.Operator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OperatorService {


    Operator save(Operator operator);

    Optional<Operator> partialUpdate(Operator operator);

    List<Operator> findAll();

    Page<Operator> findAllWithEagerRelationships(Pageable pageable);

    Optional<Operator> findOne(String id);

    void delete(String id);
}
