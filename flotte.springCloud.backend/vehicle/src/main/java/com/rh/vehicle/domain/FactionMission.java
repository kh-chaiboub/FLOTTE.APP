package com.rh.vehicle.domain;


import com.rh.vehicle.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;


/**
 * A VehiculeMission.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Document(collection = "faction_Mission")
public class FactionMission {
    private static final long serialVersionUID = 1L;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

//    @Field("mission_faction_name")
    private String factionMissionName;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
//    @Field("mission_faction_dateD")
    private Date factionMission_DateD;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
//    @Field("mission_faction_dateF")
    private Date factionMission_DateF;

    @DBRef
    @Field("intervention")
    private List<Person> personList;


}
