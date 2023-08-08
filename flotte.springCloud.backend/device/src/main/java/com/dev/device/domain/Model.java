package com.dev.device.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Model.
 */
@Document(collection = "model")
public class Model implements Serializable {

//    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("device_model")
    private String deviceModel;

//    @DBRef
    @Field("brand")
    @JsonIgnoreProperties("models")
    private Brand brand;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public Model deviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
        return this;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public Brand getBrand() {
        return brand;
    }

    public Model brand(Brand brand) {
        this.brand = brand;
        return this;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Model)) {
            return false;
        }
        return id != null && id.equals(((Model) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + getId() +
                ", deviceModel='" + getDeviceModel() + "'" +
                "}";
    }
}
