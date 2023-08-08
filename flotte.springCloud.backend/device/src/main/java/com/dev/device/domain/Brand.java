package com.dev.device.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.validation.constraints.*;

import java.io.Serializable;

import com.dev.device.domain.Category;

/**
 * A Brand.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "brand")
public class Brand implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("deviceBrand")
    private String deviceBrand;

//    @DBRef
    @Field("deviceCategory")
    @JsonIgnoreProperties("brands")
    private Category deviceCategory;




//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getDeviceBrand() {
//        return deviceBrand;
//    }

//    public Brand deviceBrand(String deviceBrand) {
//        this.deviceBrand = deviceBrand;
//        return this;
//    }
//
//    public void setDeviceBrand(String deviceBrand) {
//        this.deviceBrand = deviceBrand;
//    }
//
//    public Category getCategory() {
//        return device_category;
//    }
//
//    public Brand category(Category device_category) {
//        this.device_category = device_category;
//        return this;
//    }
//
//    public void setCategory(Category device_category) {
//        this.device_category = device_category;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (!(o instanceof Brand)) {
//            return false;
//        }
//        return id != null && id.equals(((Brand) o).id);
//    }
//
//    @Override
//    public int hashCode() {
//        return 31;
//    }
//
//    @Override
//    public String toString() {
//        return "Brand{" +
//                "id=" + getId() +
//                ", deviceBrand='" + getDeviceBrand() + "'" +
//                "}";
//    }
}
