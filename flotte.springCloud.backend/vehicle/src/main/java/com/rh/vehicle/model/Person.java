package com.rh.vehicle.model;

import com.rh.vehicle.model.enumeration.Grade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {

     private String id;
     private String firstName;
     private String lastName;
     private Date dateOfBirth;
     private String ncin;
     private String fonction;
     private Grade grade;
     private int mle;
     private String gsm;
     private RefOrgan refOrgan;


}
