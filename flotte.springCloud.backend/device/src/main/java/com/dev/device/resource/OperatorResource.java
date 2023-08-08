package com.dev.device.resource;

import com.dev.device.domain.Operator;
import com.dev.device.service.OperatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PostAuthorize;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/devices")
public class OperatorResource {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final OperatorService operatorService;

    public OperatorResource(OperatorService operatorService) {
        this.operatorService = operatorService;
    }


    @PostMapping("/operators")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Operator> createOperator(@Valid @RequestBody Operator operator) throws URISyntaxException {
        LOGGER.info("REST request to save Operator : {}", operator);

        Operator result = operatorService.save(operator);
        return ResponseEntity.created(new URI("/api/operators/" + result.getId()))
                .body(result);
    }

    @PutMapping("/operators")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Operator> updateOperator(@Valid @RequestBody Operator operator) throws URISyntaxException {
        LOGGER.info("REST request to update Operator : {}", operator);

        Operator result = operatorService.save(operator);
        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping("/operators")
    @PreAuthorize("hasAuthority('USER')")
    public List<Operator> getAllOperators() {
        LOGGER.info("REST request to get all Operators");
        return operatorService.findAll();
    }

    @GetMapping("/operators/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Operator> getOperator(@PathVariable String id) {
        LOGGER.info("REST request to get Operator : {}", id);
        Optional<Operator> operator = operatorService.findOne(id);
        return new ResponseEntity<Operator>(operator.get(), HttpStatus.FOUND);


    }

    @DeleteMapping("/operators/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteOperator(@PathVariable String id) {
        LOGGER.info("REST request to delete Operator : {}", id);
        operatorService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.GONE);
    }


}
