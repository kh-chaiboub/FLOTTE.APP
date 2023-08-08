package com.dev.device.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Operator.
 */
@Document(collection = "operator")
public class Operator implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("operator_name")
    private String operatorName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public Operator operatorName(String operatorName) {
        this.operatorName = operatorName;
        return this;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Operator)) {
            return false;
        }
        return id != null && id.equals(((Operator) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Operator{" +
                "id=" + getId() +
                ", operatorName='" + getOperatorName() + "'" +
                "}";
    }
}
