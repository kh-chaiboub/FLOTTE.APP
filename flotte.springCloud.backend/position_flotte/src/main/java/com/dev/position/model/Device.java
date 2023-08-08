package com.dev.position.model;

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


    private String status;


    private Instant lastUpdate;



}
