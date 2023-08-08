package com.dev.position.service.impl;

import com.dev.position.domain.Position;
import com.dev.position.model.Device;
import com.dev.position.model.DeviceClient;
import com.dev.position.repository.PositionRepository;
import com.dev.position.service.PositionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class PositionServiceImpl implements PositionService {


    Logger log = LoggerFactory.getLogger(this.getClass());
    private PositionRepository positionRepositiory;
    private DeviceClient deviceClient;

    public PositionServiceImpl(PositionRepository positionRepositiory, DeviceClient deviceClient) {
        this.positionRepositiory = positionRepositiory;
        this.deviceClient = deviceClient;
    }
//
//    @Override
//    public List<Position> findAll() {
//
//        log.debug("REST request to get all Positions");
//        List<Position> Listp = positionRepositiory.findAll();
//        log.info("REST request to All Positions : {}");
//        Listp.forEach(lp -> {
//           // System.out.println(lp.getDeviceID());
//            Device d = deviceClient.getDeviceByImie(lp.getDeviceID());
//           // System.out.println(lp.getDeviceID());
//            lp.setDevice(d);
//        });
//        return Listp;
//    }
//
//    @Override
//    public void delete(String id) {
//        positionRepositiory.deleteById(id);
//    }
//
//    @Override
//    public Optional<Position> getLastPositionById(String id) {
//        Optional<Position> lastPosition = positionRepositiory.findById(id);
//
//        Device d = deviceClient.getDeviceByDeviceID(lastPosition.get().getDeviceID());
//        lastPosition.get().setDevice(d);
//        return lastPosition;
//    }
//
//    @Override
//    public Optional<Position> getLastPositionByDeviceId(String deviceId) {
//        Optional<Position> lastPosition = positionRepositiory.findByDeviceID(deviceId);
//
//        //Device d = deviceClient.getDeviceByDeviceID(lastPosition.get().getDeviceID());
//        // lastPosition.get().setDevice(d);
//        return lastPosition;
//    }

    @Override
    public List<Position> findbyDeviceIdBetweenDate(String deviceId, Date dated, Date datef) {

        log.debug("REST request to get all Positions");
        List<Position> Listp = positionRepositiory.findByDeviceIDAndDeviceTimeBetween(deviceId,dated,datef);
        log.info("REST request to All Positions : {}");
//        Listp.forEach(lp -> {
//           // System.out.println(lp.getDeviceID());
//            Device d = deviceClient.getDeviceByImie(lp.getDeviceID());
//           // System.out.println(lp.getDeviceID());
//            lp.setDevice(d);
//        });
        return Listp;
    }
}
