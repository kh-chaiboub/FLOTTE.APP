package com.dev.device.resource;

import com.dev.device.domain.Brand;
import com.dev.device.domain.Model;
import com.dev.device.service.ModelService;
import com.dev.device.service.BrandService;
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
@RequestMapping("/api/models")
public class ModelResource {


    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    private static final String ENTITY_NAME = "model";
    private final ModelService modelService;
    private final  BrandService brandService;

    public ModelResource(ModelService modelService, BrandService brandService) {
        this.modelService = modelService;
        this.brandService = brandService;
    }


    @PostMapping("/models")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Model> createModel(@Valid @RequestBody Model model) throws URISyntaxException {
        LOGGER.info("REST request to save Model : {}", model);

        Model result = modelService.save(model);
        return ResponseEntity.created(new URI("/api/models/" + result.getId()))
                .body(result);
    }

    @PutMapping("/models/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Model> updateModel(@Valid @RequestBody Model model) throws URISyntaxException {
        LOGGER.info("REST request to update Model : {}", model);

        Model result = modelService.save(model);
        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping("/models")
    @PreAuthorize("hasAuthority('USER')")
    public List<Model> getAllModels() {
        LOGGER.info("REST request to get all Models");
        return modelService.findAll();
    }

    @GetMapping("/models/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Model> getModel(@PathVariable String id) {
        LOGGER.info("REST request to get Model : {}", id);
        Optional<Model> model = modelService.findOne(id);
        return new ResponseEntity<Model>(model.get(), HttpStatus.FOUND);

    }
    @GetMapping("models/brands/{deviceModel}")
    @PreAuthorize("hasAuthority('USER')")
    public List<Model> getModelByDeviceBrands(@PathVariable String deviceModel) {
        LOGGER.info("REST request to get Model : {}", deviceModel);
        Optional<Brand> brand= brandService.findOne(deviceModel);
        return modelService.findAllByBrands(brand);

    }

    @DeleteMapping("/models/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<HttpResponse> deleteModel(@PathVariable String id) {
        LOGGER.info("REST request to delete Model : {}", id);
        modelService.delete(id);
        return response(OK, "Model_Device_DELETED_SUCCESSFULLY");
    }


    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }

}
