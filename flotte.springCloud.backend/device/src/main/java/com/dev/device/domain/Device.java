package com.dev.device.domain;

import ch.qos.logback.classic.util.CopyOnInheritThreadLocal;
import com.dev.device.model.LastPosition;
import com.dev.device.model.RefOrgan;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.dev.device.enumeration.State;
import com.dev.device.enumeration.Status;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Optional;

/**
 * A Device.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "device")
public class Device implements Serializable {

    private static final long serialVersionUID = 1L;

    @Transient
    public static final String SEQUENCE_NAME = "devices_sequence";

    @Id
    private String id;

    @NotNull
    @Field("deviceID")
    private String deviceID;

    @NotNull
    @Field("serial_number")
    private String serialNumber;

    @NotNull
    @Field("imei")
    private String imei;

    @Field("active")
    private Boolean active;

    @Field("state")
    private State state;

    @Field("status")
    private boolean status;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Field("last_update")
    private Date lastUpdate;

    @NotNull
    @Field("phone_number")
    private String phoneNumber;

    @NotNull
    @Field("sim_serial_number")
    private String simserialNumber;

    //@DBRef
    @Field("devicemodel")
    private Model devicemodel;

   @DBRef
   @Field("refOrgan")
    private RefOrgan  refOrgan;


    @Field("prefOrgan")
    private String prefOrgan;

    @Transient
    private LastPosition lastPosition;


    public String getDeviceID() {
        return this.deviceID;
    }

}
