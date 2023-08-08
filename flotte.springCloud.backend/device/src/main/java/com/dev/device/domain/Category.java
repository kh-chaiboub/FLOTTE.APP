package com.dev.device.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Category.
 */
@Document(collection = "category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("device_category")
    private String deviceCategory;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceCategory() {
        return deviceCategory;
    }

    public Category deviceCategory(String deviceCategory) {
        this.deviceCategory = deviceCategory;
        return this;
    }

    public void setDeviceCategory(String deviceCategory) {
        this.deviceCategory = deviceCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Category)) {
            return false;
        }
        return id != null && id.equals(((Category) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + getId() +
                ", deviceCategory='" + getDeviceCategory() + "'" +
                "}";
    }
}
