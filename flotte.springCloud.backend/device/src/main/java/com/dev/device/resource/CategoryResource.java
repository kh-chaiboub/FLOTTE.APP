package com.dev.device.resource;

import com.dev.device.domain.Category;
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
@RequestMapping(value = "/api/categories")
public class CategoryResource {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    private static final String ENTITY_NAME = "category";
    private final CategoryService categoryService;

    public CategoryResource(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/categories")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category) throws URISyntaxException {
        LOGGER.info("REST request to save Category : {}", category);

        Category result = categoryService.save(category);
        return ResponseEntity.created(new URI("/api/categories/" + result.getId()))
                .body(result);
    }

    @PutMapping("/categories/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Category> updateCategory(@PathVariable(value = "id", required = false) final String id,
                                                   @RequestBody Category category) throws URISyntaxException {
        LOGGER.info("REST request to update Category : {}", category);

        Category result = categoryService.save(category);
        return ResponseEntity.ok()
                .body(result);
    }


    @GetMapping("/categories")
    @PreAuthorize("hasAuthority('USER')")
    public List<Category> getAllCategories() {
        LOGGER.info("REST request to get all Categories");
        return categoryService.findAll();
    }

    @GetMapping("/categories/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Category> getCategory(@PathVariable String id) {
        LOGGER.info("REST request to get Category : {}", id);
        Optional<Category> category = categoryService.findOne(id);
        return new ResponseEntity<Category>(category.get(), HttpStatus.FOUND);
    }

    @DeleteMapping("/categories/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<HttpResponse> deleteCategory(@PathVariable String id) {
        LOGGER.info("REST request to delete Category : {}", id);
        categoryService.delete(id);
        return response(OK, "Category_Device_DELETED_SUCCESSFULLY");
    }


    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }



}
