package com.dev.device.repository;

import com.dev.device.domain.Brand;
import com.dev.device.domain.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends MongoRepository<Brand, String> {
     public List<Brand> findByDeviceCategory(Optional<Category> category);
}
