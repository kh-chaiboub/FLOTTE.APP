package com.rh.vehicle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Data

@AllArgsConstructor
@NoArgsConstructor
public class Device {

    private String id;

    private String deviceID;


    private String serialNumber;


    private String imei;


    private Boolean active;


    private Instant creationDate;


    private Instant expirationDate;


    private String state;


    private boolean status;


    private Instant lastUpdate;

    private LastPosition lastPosition;


//    private String simCard;
//
//
//    private String model;
//
//
//    private String unite;

}
