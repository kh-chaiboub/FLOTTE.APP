package com.rh.vehicle.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A EtatVehicule.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "etat_vehicule")
public class EtatVehicule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("vehicule_status")
    private String vehicleStatus;


}
