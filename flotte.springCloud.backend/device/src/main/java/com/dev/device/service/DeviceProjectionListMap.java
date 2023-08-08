package com.dev.device.service;

import com.dev.device.domain.Device;
import com.dev.device.model.LastPosition;
import com.dev.device.model.RefOrgan;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "listDevice",types = {Device.class})
public interface DeviceProjectionListMap {
    String getId();
    String getDeviceID();
    String getActive();
    RefOrgan getRefOrgan();
    String getImei();
    String getState();
    String getPhoneNumber();
    LastPosition getLastPosition();

}
