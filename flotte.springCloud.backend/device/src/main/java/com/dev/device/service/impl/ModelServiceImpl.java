package com.dev.device.service.impl;

import com.dev.device.domain.Brand;
import com.dev.device.domain.Model;
import com.dev.device.repository.ModelRepository;
import com.dev.device.service.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ModelServiceImpl implements ModelService {

    private final Logger log = LoggerFactory.getLogger(BrandServiceImpl.class);
    private final ModelRepository modelRepository;

    public ModelServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }


    @Override
    public Model save(Model model) {
        return modelRepository.save(model);
    }

    @Override
    public Optional<Model> partialUpdate(Model model) {
        return modelRepository
                .findById(model.getId())
                .map(
                        existingModel -> {
                            if (model.getDeviceModel() != null) {
                                existingModel.setDeviceModel(model.getDeviceModel());
                            }
                            if (model.getBrand() != null) {
                                existingModel.setBrand(model.getBrand());
                            }
                            return existingModel;
                        }

                )
                .map(modelRepository::save);
    }


    @Override
    public List<Model> findAll() {
        log.debug("REST request to get all Models");
        return modelRepository.findAll();
    }

    @Override
    public List<Model> findAllByBrands(Optional<Brand> brand) {
        log.debug("REST request to get all Models brands");
        return modelRepository.findByBrand(brand);
    }

    @Override
    public Page<Model> findAllWithEagerRelationships(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Model> findOne(String id) {
        log.debug("REST request to get Model : {}", id);
        Optional<Model> model = modelRepository.findById(id);
        return model;
    }

    @Override
    public void delete(String id) {
        log.debug("REST request to delete Model : {}", id);
        modelRepository.deleteById(id);
    }
}
