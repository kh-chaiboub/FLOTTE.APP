package com.dev.device.model;


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
import java.util.Date;

/**
 * A LastPosition.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "last_position")
public class LastPosition implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String SEQUENCE_NAME = "lastPositions_sequence";

    private String id;

    private Double positionID;

    private Integer msLink;

    private Double accuracy;

    private String address;


    private Double course;


    private Double speed;


    private Double direction;


    private Double altitude;


    private Double latitude;

    private Double longitude;


    private Boolean valid;


    private Boolean outdated;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")

    private Date crationDate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")

    private Date fixTime;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")

    private Date deviceTime;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")

    private Date serverTime;


    private String protocol;


    private Integer mcc;


    private Integer mnc;


    private Integer lac;


    private Double cid;


    private Integer rssi;



    private String deviceID;


    private LocalitePosition localite;


    private String vitesseColor;
}
