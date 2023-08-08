package com.dev.device.repository;

import com.dev.device.domain.Brand;
import com.dev.device.domain.Model;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ModelRepository extends MongoRepository<Model, String> {
    public List<Model> findByBrand(Optional<Brand> brand);

}
