package com.dev.device.service;


import com.dev.device.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CategoryService {


    Category save(Category category);


    Optional<Category> partialUpdate(Category category);


    List<Category> findAll();

    Page<Category> findAllWithEagerRelationships(Pageable pageable);

    Optional<Category> findOne(String id);


    void delete(String id);


}
