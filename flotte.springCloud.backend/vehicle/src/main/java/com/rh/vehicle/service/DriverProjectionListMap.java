package com.rh.vehicle.service;

import com.rh.vehicle.domain.Driver;
import com.rh.vehicle.domain.Vehicle;
import com.rh.vehicle.domain.VehicleModel;
import com.rh.vehicle.model.Device;
import com.rh.vehicle.model.LastPosition;
import com.rh.vehicle.model.Person;
import com.rh.vehicle.model.RefOrgan;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "listDriver",types = {Driver.class})
public interface DriverProjectionListMap {
    String getId();
    String getNpermis();
    String getCategoryPermis();
    Person getPerson();

}
