package com.rh.vehicle.domain;

import java.io.Serializable;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A VehicleBrands.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "vehicule_brands")
public class VehicleBrands implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("brand_name")
    private String vehicleBrand;

    @NotNull
    @Field("vehiculeTypes")
    private VehiculeTypes vehiculeType;

}
