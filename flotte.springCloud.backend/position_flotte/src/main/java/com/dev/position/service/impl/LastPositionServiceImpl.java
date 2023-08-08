package com.dev.position.service.impl;

import com.dev.position.domain.LastPosition;
import com.dev.position.model.Device;
import com.dev.position.model.DeviceClient;
import com.dev.position.repository.LastPositionRepository;
import com.dev.position.service.LastPositionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class LastPositionServiceImpl implements LastPositionService {


    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LastPositionRepository lastPositionRepositiory;
    @Autowired
    private DeviceClient deviceClient;

    public LastPositionServiceImpl(LastPositionRepository lastPositionRepositiory, DeviceClient deviceClient) {
        this.lastPositionRepositiory = lastPositionRepositiory;
        this.deviceClient = deviceClient;
    }

    @Override
    public List<LastPosition> findAll() {

        log.debug("REST request to get all Positions");
        List<LastPosition> Listp = lastPositionRepositiory.findAll();
        log.info("REST request to All Positions : {}");
        Listp.forEach(lp -> {
            if(lp.getDeviceID()!=null){
                Device d = deviceClient.getDeviceByImie(lp.getDeviceID());
                if(d!=null){  lp.setDevice(d);}
            }



        });
        return Listp;
    }

    @Override
    public void delete(String id) {
        lastPositionRepositiory.deleteById(id);
    }

    @Override
    public Optional<LastPosition> getLastPositionById(String id) {
        Optional<LastPosition> lastPosition = lastPositionRepositiory.findById(id);

        Device d = deviceClient.getDeviceByDeviceID(lastPosition.get().getDeviceID());
        lastPosition.get().setDevice(d);
        return lastPosition;
    }

    @Override
    public Optional<LastPosition> getLastPositionByDeviceId(String deviceId) {
        Optional<LastPosition> lastPosition = lastPositionRepositiory.findByDeviceID(deviceId);

        //Device d = deviceClient.getDeviceByDeviceID(lastPosition.get().getDeviceID());
        // lastPosition.get().setDevice(d);
        return lastPosition;
    }
}
