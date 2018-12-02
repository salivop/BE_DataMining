package com.vaitkevicius.main.common.errors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * *
 * Created By Povilas 14/11/2018
 * *
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class FieldErrorResource {

    private String field;
    private String code;
    private String message;

    public String getField() { return field; }

    public void setField(String field) { this.field = field; }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }
}
