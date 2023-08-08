package com.rh.vehicle.domain;

import java.io.Serializable;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A VehicleModel.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "vehicule_model")
public class VehicleModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("model_name")
    private String modelName;

    @Field("defaut_image")
    private String defautImage;

    @Field("mouve_D_image")
    private String mouve_D_Image;

    @Field("mouve_G_image")
    private String mouve_G_Image;

    @Field("stop_D_image")
    private String stop_D_Image;

    @Field("stop_G_image")
    private String stop_G_Image;

    @Field("Alert_Mission_image")
    private String alert_Mission_Image;

    @DBRef
    @Field("vehicleBrands")
    @JsonIgnoreProperties("vehiclemodel")
    private VehicleBrands vehicleBrands;

}
