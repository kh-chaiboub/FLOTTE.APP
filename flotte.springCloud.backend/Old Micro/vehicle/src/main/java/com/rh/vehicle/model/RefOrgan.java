package com.rh.vehicle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * A RefOrgan.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefOrgan{
    private String id;
    @Field("ref_organ_name")
    private String refOrganName;
    private String prefOrgan;






}
