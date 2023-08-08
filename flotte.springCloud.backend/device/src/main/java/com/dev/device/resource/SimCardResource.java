package com.dev.device.resource;


import com.dev.device.domain.SimCard;
import com.dev.device.service.SimCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/devices")
public class SimCardResource {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final SimCardService simCardService;


    public SimCardResource(SimCardService simCardService) {
        this.simCardService = simCardService;
    }

    @PostMapping("/sim-cards")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<SimCard> createSimCard(@Valid @RequestBody SimCard simCard) throws URISyntaxException {
        LOGGER.info("REST request to save SimCard : {}", simCard);

        SimCard result = simCardService.save(simCard);
        return ResponseEntity.created(new URI("/api/sim-cards/" + result.getId()))
                .body(result);
    }

    @PutMapping("/sim-cards")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<SimCard> updateSimCard(@Valid @RequestBody SimCard simCard) throws URISyntaxException {
        LOGGER.info("REST request to update SimCard : {}", simCard);

        Optional<SimCard> result = simCardService.partialUpdate(simCard);
        return ResponseEntity.ok()
                .body(result.get());


    }

    @GetMapping("/sim-cards")
    @PreAuthorize("hasAuthority('USER')")
    public List<SimCard> getAllSimCards() {
        LOGGER.info("REST request to get all SimCards");
        return simCardService.findAll();
    }

    @GetMapping("/sim-cards/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<SimCard> getSimCard(@PathVariable String id) {
        LOGGER.info("REST request to get SimCard : {}", id);
        Optional<SimCard> simCard = simCardService.findOne(id);
        return new ResponseEntity<SimCard>(simCard.get(), HttpStatus.FOUND);
    }

    @DeleteMapping("/sim-cards/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteSimCard(@PathVariable String id) {
        LOGGER.info("REST request to delete SimCard : {}", id);
        simCardService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.GONE);
    }


}
