package com.dev.position.service;

import com.dev.position.domain.Position;

import java.util.Date;
import java.util.List;

public interface PositionService {
    static void delete(String id) {
    }

    List<Position> findbyDeviceIdBetweenDate(String deviceId, Date dated, Date datef);
}
