package com.dev.device.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import org.springframework.data.annotCation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
//import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.validation.constraints.*;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.io.Serializable;

/**
 * A SimCard.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "sim_card")
public class SimCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("phone_number")
    private String phoneNumber;

    @NotNull
    @Field("serial_number")
    private String serialNumber;

    @DBRef
    @Field("operator")
    @JsonIgnoreProperties("simCards")
    private Operator operator;

//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public Integer getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public SimCard phoneNumber(Integer phoneNumber) {
//        this.phoneNumber = phoneNumber;
//        return this;
//    }
//
//    public void setPhoneNumber(Integer phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public String getSerialNumber() {
//        return serialNumber;
//    }
//
//    public SimCard serialNumber(String serialNumber) {
//        this.serialNumber = serialNumber;
//        return this;
//    }
//
//    public void setSerialNumber(String serialNumber) {
//        this.serialNumber = serialNumber;
//    }
//
//    public Operator getOperator() {
//        return operator;
//    }
//
//    public SimCard operator(Operator operator) {
//        this.operator = operator;
//        return this;
//    }
//
//    public void setOperator(Operator operator) {
//        this.operator = operator;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (!(o instanceof SimCard)) {
//            return false;
//        }
//        return id != null && id.equals(((SimCard) o).id);
//    }
//
//    @Override
//    public int hashCode() {
//        return 31;
//    }
//
//    @Override
//    public String toString() {
//        return "SimCard{" +
//                "id=" + getId() +
//                ", phoneNumber=" + getPhoneNumber() +
//                ", serialNumber='" + getSerialNumber() + "'" +
//                "}";
//    }
}
