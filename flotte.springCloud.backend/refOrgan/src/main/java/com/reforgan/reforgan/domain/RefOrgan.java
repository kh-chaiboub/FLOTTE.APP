package com.reforgan.reforgan.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;

/**
 * A RefOrgan.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "refOrgan")
public class RefOrgan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("ref_organ_name")
    private String refOrganName;


    @Field("prefOrgan")
    private String prefOrgan;

}
