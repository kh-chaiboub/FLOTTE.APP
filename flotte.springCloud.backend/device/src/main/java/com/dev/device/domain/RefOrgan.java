//package com.dev.device.domain;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Field;
//import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.data.mongodb.core.mapping.DBRef;
//
//import java.io.Serializable;
//
///**
// * A RefOrgan.
// */
//@Document(collection = "ref_organ")
//public class RefOrgan implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    private String id;
//
//    @Field("ref_organ_name")
//    private String refOrganName;
//
//    @DBRef
//    @Field("serviceGr")
//    @JsonIgnoreProperties("refOrgans")
//    private RefOrgan serviceGr;
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getRefOrganName() {
//        return refOrganName;
//    }
//
//    public RefOrgan refOrganName(String refOrganName) {
//        this.refOrganName = refOrganName;
//        return this;
//    }
//
//    public void setRefOrganName(String refOrganName) {
//        this.refOrganName = refOrganName;
//    }
//
//    public RefOrgan getServiceGr() {
//        return serviceGr;
//    }
//
//    public RefOrgan serviceGr(RefOrgan refOrgan) {
//        this.serviceGr = refOrgan;
//        return this;
//    }
//
//    public void setServiceGr(RefOrgan refOrgan) {
//        this.serviceGr = refOrgan;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (!(o instanceof RefOrgan)) {
//            return false;
//        }
//        return id != null && id.equals(((RefOrgan) o).id);
//    }
//
//    @Override
//    public int hashCode() {
//        return 31;
//    }
//
//    @Override
//    public String toString() {
//        return "RefOrgan{" +
//                "id=" + getId() +
//                ", refOrganName='" + getRefOrganName() + "'" +
//                "}";
//    }
//}
