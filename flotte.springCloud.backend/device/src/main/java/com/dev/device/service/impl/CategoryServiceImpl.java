package com.dev.device.service.impl;

import com.dev.device.domain.Category;
import com.dev.device.repository.CategoryRepository;
import com.dev.device.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private static final String ENTITY_NAME = "category";


    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Category save(Category category) {
        log.debug("REST request to save Category : {}", category);

        return categoryRepository.save(category);

    }

    @Override
    public Optional<Category> partialUpdate(Category category) {
        log.debug("REST request to update Category : {}", category);


        return categoryRepository
                .findById(category.getId())
                .map(
                        existingCategory -> {
                            if (category.getDeviceCategory() != null) {
                                existingCategory.setDeviceCategory(category.getDeviceCategory());
                            }
                            return existingCategory;
                        }
                ).map(categoryRepository::save);
    }

    @Override
    public List<Category> findAll() {
        log.debug("Request to get all Categorys");
        return categoryRepository.findAll();
    }

    @Override
    public Page<Category> findAllWithEagerRelationships(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Category> findOne(String id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        categoryRepository.deleteById(id);

    }

}
