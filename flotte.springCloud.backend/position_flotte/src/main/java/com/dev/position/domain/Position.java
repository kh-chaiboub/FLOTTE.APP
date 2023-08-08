package com.dev.position.domain;


import com.dev.position.model.Device;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;


/**
 * A Position.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "position")
public class Position implements Serializable {

    private static final long serialVersionUID = 1L;

    @Transient
    public static final String SEQUENCE_NAME = "Positions_sequence";


    @Id
    private String id;

    @Field("position_id")
    private Double positionID;

    @Field("ms_link")
    private Integer msLink;

    @Field("accuracy")
    private Double accuracy;

    @Field("address")
    private String address;

    @Field("course")
    private Double course;

    @Field("speed")
    private Double speed;

    @Field("direction")
    private Double direction;

    @Field("altitude")
    private Double altitude;

    @Field("latitude")
    private Double latitude;

    @Field("longitude")
    private Double longitude;

    @Field("valid")
    private Boolean valid;

    @Field("outdated")
    private Boolean outdated;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Field("cration_date")
    private Date crationDate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Field("fix_time")
    private Date fixTime;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Field("deviceTime")
    private Date deviceTime;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Field("serverTime")
    private Date serverTime;

    @Field("protocol")
    private String protocol;

    @Field("mcc")
    private Integer mcc;

    @Field("mnc")
    private Integer mnc;

    @Field("lac")
    private Integer lac;

    @Field("cid")
    private Double cid;

    @Field("rssi")
    private Integer rssi;

    @Transient
    private Device device;

    @Field("deviceID")
    private String deviceID;


    @Field("localite")
    private String localite;

    @DBRef
    @Field("vitesseColor")

    private String vitesseColor;

}
