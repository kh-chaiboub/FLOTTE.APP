package com.rh.vehicle.domain;

import com.rh.vehicle.domain.enumeration.FuelType;
import com.rh.vehicle.model.Device;
import com.rh.vehicle.model.LastPosition;
import com.rh.vehicle.model.RefOrgan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
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
@Document(collection = "vehicule")
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;


    @Field("registrationnumber")
    private String registrationNumber;

//    @Field("available")
//    private Boolean available;


    @Field("frequence")
    private String frequence;

    @Field("indicatif")
    private String indicatif;

    @Field("vehiculedesc")
    private String vehiculeDesc;

    @Field("vehiculemaxvitesse")
    private float vehiculeMaxVitesse;

    @Field("vehiculekmestime")
    private float vehiculeKmEstime;

    //    @Temporal(TemporalType.DATE)
//    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Field("vehiculekmdate")
    private String vehiculeKmDate;

  //  @DBRef
    @Field("vehiclemodel")
//   @JsonIgnoreProperties(value = {"vehicleBrands"}, allowSetters = false)
    private VehicleModel vehicleModel;


  //  @DBRef
    @Field("etatvehicule")
    private EtatVehicule etatVehicule;


    @Field("fueltype")
    private FuelType fuelType;

  //  @DBRef
    @Field("vehicletype")
    private VehiculeTypes vehicleType;


//    @DBRef
//    @Field("vehiculeMission")
//    private VehiculeMission vehiculeMission;

//    @Transient
//    private RefOrgan refOrgan;

    @DBRef
    @Field("refOrgan")
    private RefOrgan  refOrgan;

    @Field("prefOrgan")
    private String prefOrgan;

  //  @DBRef
    @Field("driver")
    private Driver driver;

    //    @DBRef
//    @Field("intervention")
//    private Set<Person> intervention = new HashSet<>();


    //@ReadOnlyProperty
   // @DBRef
    @Field("device")
    private Device device;

    //    @Field("deviceID")
//    private String deviceID;

    @Field("lastPosition")
    private LastPosition lastPosition;
//    @Field("lastposition")
//    private String lastPosition;


//    public String getRegistrationNumber() {
//        return registrationNumber;
//    }
//
//    public RefOrgan getRefOrgan() {
//        return refOrgan;
//    }
//
//    public Device getDevice() {
//        return device;
//    }
}
