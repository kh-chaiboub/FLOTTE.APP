package com.dev.position.service;


import com.dev.position.domain.LastPosition;

import java.util.List;
import java.util.Optional;

public interface LastPositionService {


    List<LastPosition> findAll();

    void delete(String id);

    Optional<LastPosition> getLastPositionById(String id);

    Optional<LastPosition> getLastPositionByDeviceId(String deviceId);

}
