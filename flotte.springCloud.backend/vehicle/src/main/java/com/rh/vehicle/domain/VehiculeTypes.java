package com.rh.vehicle.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * A VehicleTypes.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "vehicule_types")
public class VehiculeTypes implements Serializable {
    //bus camion moto....
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Field("vehiculeType")
    private String vehiculeType;

}
