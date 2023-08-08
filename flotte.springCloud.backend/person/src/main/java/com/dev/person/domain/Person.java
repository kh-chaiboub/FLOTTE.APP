package com.dev.person.domain;

import com.dev.person.enumeration.Grade;
import com.dev.person.model.RefOrgan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import org.springframework.data.annotation.Transient;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "person")
public class Person implements Serializable {
    private static final long serialVersionID = 1L;
    @Transient
    public static final String SEQUENCE_NAME = "persons_sequence";
    @Id
    private String id;
    @Field("firstName")
    private String firstName;
    @Field("lastName")
    private String lastName;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Field("dateOfBirth")
    private Date dateOfBirth;

    @Field("ncin")
    private String ncin;

    @Field("fonction")
    private String fonction;

    @Field("grade")
    private Grade grade;

    @Field("mle")
    private int mle;

    @Field("gsm")
    private String gsm;

    @DBRef
    @Field("refOrgan")
    private RefOrgan refOrgan;

    @Field("prefOrgan")
    private String prefOrgan;

}
