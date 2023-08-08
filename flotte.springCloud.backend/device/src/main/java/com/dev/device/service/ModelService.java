package com.dev.device.service;

import com.dev.device.domain.Brand;
import com.dev.device.domain.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ModelService {


    Model save(Model model);


    Optional<Model> partialUpdate(Model model);


    List<Model> findAll();

    List<Model> findAllByBrands(Optional<Brand> brand);

    Page<Model> findAllWithEagerRelationships(Pageable pageable);

    Optional<Model> findOne(String id);


    void delete(String id);

}
