package com.rh.vehicle.domain;

import com.rh.vehicle.domain.enumeration.FuelType;
import com.rh.vehicle.model.Device;
import com.rh.vehicle.model.RefOrgan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * A Vehicle.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "vehiculeNew")
public class VehicleNew implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;


    @Field("text")
    private String text;

//    @Field("available")
//    private Boolean available;


    @Field("value")
    private Vehicle vehicle;

    @Field("prefOrgan")
    private String prefOrgan;

    @Field("collapsed")
    private Boolean collapsed;

    @Field("checked")
    private Boolean checked;

    @Field("iStracking")
    private Boolean iStracking;




}
