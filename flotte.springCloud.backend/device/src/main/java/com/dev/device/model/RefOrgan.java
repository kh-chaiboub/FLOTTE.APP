package com.dev.device.model;

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
@ToString
@Document(collection = "refOrgan")
public class RefOrgan{
    @Id
    private String id;

    @Field("ref_organ_name")
    private String refOrganName;

    @DBRef
    @Field("refOrgan")
    private RefOrgan refOrgan;

}
