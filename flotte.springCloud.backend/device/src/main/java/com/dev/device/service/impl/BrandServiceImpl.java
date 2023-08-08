package com.dev.device.service.impl;

import com.dev.device.domain.Brand;
import com.dev.device.domain.Category;
import com.dev.device.repository.BrandRepository;
import com.dev.device.service.BrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


/**
 * Service Implementation for managing {@link Brand}.
 */
@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    private final Logger log = LoggerFactory.getLogger(BrandServiceImpl.class);
    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }


    @Override
    public Brand save(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Optional<Brand> partialUpdate(Brand brand) {

        return brandRepository
                .findById(brand.getId())
                .map(
                        existingBrand -> {
                            if (brand.getDeviceBrand() != null) {
                                existingBrand.setDeviceBrand(brand.getDeviceBrand());
                            }
                            if (brand.getDeviceCategory() != null) {
                                existingBrand.setDeviceCategory(brand.getDeviceCategory());
                            }
                            return existingBrand;
                        }

                )
                .map(brandRepository::save);
    }

    @Override
    public List<Brand> findAll() {
        log.debug("Request to get all Brands");
        return brandRepository.findAll();
    }

    @Override
    public List<Brand> findAllByCategory( Optional<Category> category) {
        return brandRepository.findByDeviceCategory(category);
    }

    @Override
    public Page<Brand> findAllWithEagerRelationships(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Brand> findOne(String id) {
        return brandRepository.findById(id);
    }


    @Override
    public void delete(String id) {
        brandRepository.deleteById(id);

    }


}
