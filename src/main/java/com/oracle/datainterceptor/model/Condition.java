package com.oracle.datainterceptor.model;

import java.io.Serializable;

/**
 * @author velisela
 *  This is a model class which holds the condition for query 
 */

public class Condition implements Serializable {

    private String field;
    private String value;

    public Condition() {

    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    
}
