package com.dev.device.resource;


import com.dev.device.domain.Brand;
import com.dev.device.domain.Category;
import com.dev.device.service.BrandService;
import com.dev.device.service.CategoryService;
import com.dev.device.util.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/api/brands")
public class BrandResource {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());


    private static final String ENTITY_NAME = "brand";

    private final BrandService brandService;
    private final CategoryService categoryService;


    public BrandResource(BrandService brandService,CategoryService categoryService) {

        this.brandService = brandService;
        this.categoryService = categoryService;
    }

    @PostMapping("/brands")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Brand> createBrand(@Valid @RequestBody Brand brand) throws URISyntaxException {
        LOGGER.info("REST request to save Brand : {}", brand);
        Brand result = brandService.save(brand);
        return ResponseEntity.created(new URI("/api/brands/" + result.getId()))
                .body(result);
    }


    @PutMapping("/brands/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Brand> updateBrand(@PathVariable(value = "id", required = false) final String id,
                                             @RequestBody Brand brand) throws URISyntaxException {
        LOGGER.info("REST request to update Brand : {}", brand);
        if (brand.getId() == null) {
        }
        Brand result = brandService.save(brand);
        return ResponseEntity.ok()
                .body(result);
    }


    @GetMapping("/brands")
    public List<Brand> getAllBrands() {
        LOGGER.info("REST request to get all Brands");
        return brandService.findAll();
    }

    @GetMapping("/brands/category/{deviceCategory}")
    @PreAuthorize("hasAuthority('USER')")
    public List<Brand> getBrandByDeviceCategory(@PathVariable String deviceCategory) {
        LOGGER.info("REST request to get Brand : {}", deviceCategory);
            Optional<Category> category = categoryService.findOne(deviceCategory);
            return brandService.findAllByCategory(category);

    }
    @GetMapping("/brands/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Brand> getBrandById(@PathVariable String id) {
        LOGGER.info("REST request to get Brand : {}", id);
        Optional<Brand> brand = brandService.findOne(id);
        return new ResponseEntity<Brand>(brand.get(), HttpStatus.FOUND);
    }

    @DeleteMapping("/brands/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<HttpResponse> deleteBrand(@PathVariable String id) {
        LOGGER.info("REST request to delete Brand : {}", id);
        brandService.delete(id);
        return response(OK, "Brand_DELETED_SUCCESSFULLY");

    }


    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }


}
