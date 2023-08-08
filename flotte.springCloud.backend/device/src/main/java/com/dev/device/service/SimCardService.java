package com.dev.device.service;


import com.dev.device.domain.SimCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SimCardService {
    SimCard save(SimCard simCard);

    Optional<SimCard> partialUpdate(SimCard simCard);

    List<SimCard> findAll();

    Page<SimCard> findAllWithEagerRelationships(Pageable pageable);

    Optional<SimCard> findOne(String id);

    void delete(String id);
}

