package com.rh.vehicle.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A Fuel.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "fuel")
public class Fuel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("fuel")
    private String fuel;

}
