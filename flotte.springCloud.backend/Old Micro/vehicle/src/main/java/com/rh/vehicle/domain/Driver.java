package com.rh.vehicle.domain;


import com.rh.vehicle.domain.enumeration.CategoryPermis;
import com.rh.vehicle.model.Person;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document(collection = "driver")
public class Driver {
    @Id
    private String id;


    @Field("nbrmilitaire")
    private String nbrmilitaire;


    @Field("npermis")
    private String npermis;

//    @DBRef
    @Field("categoryPermis")
    //private CategoryPermis categoryPermis;
    private  List categoryPermis= new ArrayList<>();;


    @Field("mle")
    private int mle;

//    @Transient
    @DBRef
    @Field("person")
    private Person person;



}
