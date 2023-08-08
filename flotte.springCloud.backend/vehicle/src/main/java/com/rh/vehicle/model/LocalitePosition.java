package com.rh.vehicle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalitePosition {

    private String localite;
    private String region;
    private String  cie;
    private String  bt;

}
