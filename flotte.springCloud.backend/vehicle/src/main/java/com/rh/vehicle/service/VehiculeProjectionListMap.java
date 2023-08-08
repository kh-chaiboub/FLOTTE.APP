package com.rh.vehicle.service;

import com.rh.vehicle.domain.Vehicle;
import com.rh.vehicle.domain.VehicleModel;
import com.rh.vehicle.model.Device;
import com.rh.vehicle.model.LastPosition;
import com.rh.vehicle.model.RefOrgan;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "listVehicule",types = {Vehicle.class})
public interface VehiculeProjectionListMap {
    String getId();
    String getRegistrationNumber();
    String getPrefOrgan();
    RefOrgan getRefOrgan();
     Device getDevice();
    float getVehiculeMaxVitesse();
    LastPosition getLastPosition();
    VehicleModel getVehicleModel();


}
