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
 * A LastPosition.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "last_position")
public class LastPosition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Transient
    public static final String SEQUENCE_NAME = "lastPositions_sequence";


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

//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public Double getPositionID() {
//        return positionID;
//    }
//
//    public LastPosition positionID(Double positionID) {
//        this.positionID = positionID;
//        return this;
//    }
//
//    public void setPositionID(Double positionID) {
//        this.positionID = positionID;
//    }
//
//    public Integer getMsLink() {
//        return msLink;
//    }
//
//    public LastPosition msLink(Integer msLink) {
//        this.msLink = msLink;
//        return this;
//    }
//
//    public void setMsLink(Integer msLink) {
//        this.msLink = msLink;
//    }
//
//    public Double getAccuracy() {
//        return accuracy;
//    }
//
//    public LastPosition accuracy(Double accuracy) {
//        this.accuracy = accuracy;
//        return this;
//    }
//
//    public void setAccuracy(Double accuracy) {
//        this.accuracy = accuracy;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public LastPosition address(String address) {
//        this.address = address;
//        return this;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public Double getCourse() {
//        return course;
//    }
//
//    public LastPosition course(Double course) {
//        this.course = course;
//        return this;
//    }
//
//    public void setCourse(Double course) {
//        this.course = course;
//    }
//
//    public Double getSpeed() {
//        return speed;
//    }
//
//    public LastPosition speed(Double speed) {
//        this.speed = speed;
//        return this;
//    }
//
//    public void setSpeed(Double speed) {
//        this.speed = speed;
//    }
//
//    public Double getDirection() {
//        return direction;
//    }
//
//    public LastPosition direction(Double direction) {
//        this.direction = direction;
//        return this;
//    }
//
//    public void setDirection(Double direction) {
//        this.direction = direction;
//    }
//
//    public Double getAltitude() {
//        return altitude;
//    }
//
//    public LastPosition altitude(Double altitude) {
//        this.altitude = altitude;
//        return this;
//    }
//
//    public void setAltitude(Double altitude) {
//        this.altitude = altitude;
//    }
//
//    public Double getLatitude() {
//        return latitude;
//    }
//
//    public LastPosition latitude(Double latitude) {
//        this.latitude = latitude;
//        return this;
//    }
//
//    public void setLatitude(Double latitude) {
//        this.latitude = latitude;
//    }
//
//    public Double getLongitude() {
//        return longitude;
//    }
//
//    public LastPosition longitude(Double longitude) {
//        this.longitude = longitude;
//        return this;
//    }
//
//    public void setLongitude(Double longitude) {
//        this.longitude = longitude;
//    }
//
//    public Boolean isValid() {
//        return valid;
//    }
//
//    public LastPosition valid(Boolean valid) {
//        this.valid = valid;
//        return this;
//    }
//
//    public void setValid(Boolean valid) {
//        this.valid = valid;
//    }
//
//    public Boolean isOutdated() {
//        return outdated;
//    }
//
//    public LastPosition outdated(Boolean outdated) {
//        this.outdated = outdated;
//        return this;
//    }
//
//    public void setOutdated(Boolean outdated) {
//        this.outdated = outdated;
//    }
//
//    public Instant getCrationDate() {
//        return crationDate;
//    }
//
//    public LastPosition crationDate(Instant crationDate) {
//        this.crationDate = crationDate;
//        return this;
//    }
//
//    public void setCrationDate(Instant crationDate) {
//        this.crationDate = crationDate;
//    }
//
//    public Instant getFixTime() {
//        return fixTime;
//    }
//
//    public LastPosition fixTime(Instant fixTime) {
//        this.fixTime = fixTime;
//        return this;
//    }
//
//    public void setFixTime(Instant fixTime) {
//        this.fixTime = fixTime;
//    }
//
//    public Instant getDeviceTime() {
//        return deviceTime;
//    }
//
//    public LastPosition deviceTime(Instant deviceTime) {
//        this.deviceTime = deviceTime;
//        return this;
//    }
//
//    public void setDeviceTime(Instant deviceTime) {
//        this.deviceTime = deviceTime;
//    }
//
//    public Instant getServerTime() {
//        return serverTime;
//    }
//
//    public LastPosition serverTime(Instant serverTime) {
//        this.serverTime = serverTime;
//        return this;
//    }
//
//    public void setServerTime(Instant serverTime) {
//        this.serverTime = serverTime;
//    }
//
//    public String getProtocol() {
//        return protocol;
//    }
//
//    public LastPosition protocol(String protocol) {
//        this.protocol = protocol;
//        return this;
//    }
//
//    public void setProtocol(String protocol) {
//        this.protocol = protocol;
//    }
//
//    public Integer getMcc() {
//        return mcc;
//    }
//
//    public LastPosition mcc(Integer mcc) {
//        this.mcc = mcc;
//        return this;
//    }
//
//    public void setMcc(Integer mcc) {
//        this.mcc = mcc;
//    }
//
//    public Integer getMnc() {
//        return mnc;
//    }
//
//    public LastPosition mnc(Integer mnc) {
//        this.mnc = mnc;
//        return this;
//    }
//
//    public void setMnc(Integer mnc) {
//        this.mnc = mnc;
//    }
//
//    public Integer getLac() {
//        return lac;
//    }
//
//    public LastPosition lac(Integer lac) {
//        this.lac = lac;
//        return this;
//    }
//
//    public void setLac(Integer lac) {
//        this.lac = lac;
//    }
//
//    public Double getCid() {
//        return cid;
//    }
//
//    public LastPosition cid(Double cid) {
//        this.cid = cid;
//        return this;
//    }
//
//    public void setCid(Double cid) {
//        this.cid = cid;
//    }
//
//    public Integer getRssi() {
//        return rssi;
//    }
//
//    public LastPosition rssi(Integer rssi) {
//        this.rssi = rssi;
//        return this;
//    }
//
//    public void setRssi(Integer rssi) {
//        this.rssi = rssi;
//    }
//
//    //////////////////////////////////////////////////////////////////////////////
//    public Device getDevice() {
//        return device;
//    }
//
//    public LastPosition device(Device device) {
//        this.device = device;
//        return this;
//    }
//
//    public void setDevice(Device device) {
//        this.device = device;
//    }
//
//
//    public String getDeviceID() {
//        return deviceID;
//    }
//
//    public void setDeviceID(String deviceID) {
//        this.deviceID = deviceID;
//    }
//    ///////////////////////////////////////////////////////////////////////////
//
//
//    public String getLocalites() {
//        return localite;
//    }
//
//    public LastPosition localites(String localite) {
//        this.localite = localite;
//        return this;
//    }
//
//    public void setLocalites(String localites) {
//        this.localite = localite;
//    }
//
//    public String getVitesseColor() {
//        return vitesseColor;
//    }
//
//    public LastPosition vitesseColor(String vitesseColor) {
//        this.vitesseColor = vitesseColor;
//        return this;
//    }
//
//    public void setVitesseColor(String vitesseColor) {
//        this.vitesseColor = vitesseColor;
//    }
//    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (!(o instanceof LastPosition)) {
//            return false;
//        }
//        return id != null && id.equals(((LastPosition) o).id);
//    }
//
//    @Override
//    public int hashCode() {
//        return 31;
//    }
//
//    @Override
//    public String toString() {
//        return "LastPosition{" +
//                "id=" + getId() +
//                ", positionID=" + getPositionID() +
//                ", msLink=" + getMsLink() +
//                ", accuracy=" + getAccuracy() +
//                ", address='" + getAddress() + "'" +
//                ", course=" + getCourse() +
//                ", speed=" + getSpeed() +
//                ", direction=" + getDirection() +
//                ", altitude=" + getAltitude() +
//                ", latitude=" + getLatitude() +
//                ", longitude=" + getLongitude() +
//                ", valid='" + isValid() + "'" +
//                ", outdated='" + isOutdated() + "'" +
//                ", crationDate='" + getCrationDate() + "'" +
//                ", fixTime='" + getFixTime() + "'" +
//                ", deviceTime='" + getDeviceTime() + "'" +
//                ", serverTime='" + getServerTime() + "'" +
//                ", protocol='" + getProtocol() + "'" +
//                ", mcc=" + getMcc() +
//                ", mnc=" + getMnc() +
//                ", lac=" + getLac() +
//                ", cid=" + getCid() +
//                ", rssi=" + getRssi() +
//                "}";
//    }
}
