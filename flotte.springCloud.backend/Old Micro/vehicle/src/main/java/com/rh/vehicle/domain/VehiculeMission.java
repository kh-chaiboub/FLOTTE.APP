package com.rh.vehicle.domain;


import com.rh.vehicle.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/**
 * A VehiculeMission.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "vehicule_mission")
public class VehiculeMission {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Field("mission_vehicule_name")
    private String missionVehiculeName;

//    @Temporal(TemporalType.DATE)
//    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
//    @Field("mission_vehicule_date")
//    private Date mission_Date;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Field("mission_vehicule_dateD")
    private Date mission_DateD;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Field("mission_vehicule_dateF")
    private Date mission_DateF;

//    @DBRef
    @Field("factionMission")
    private List<FactionMission> factionMissionList;

    @DBRef
    @Field("vehicule")
    private Vehicle vehicule;

    @Field("description_mission")
    private String descriptionMission;


}
