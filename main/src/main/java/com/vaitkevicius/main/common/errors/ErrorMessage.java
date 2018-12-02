package com.vaitkevicius.main.common.errors;

/**
 * *
 * Created By Povilas 14/11/2018
 * *
 **/

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vaitkevicius.main.common.httpresponsemessage.BaseMessage;
import com.vaitkevicius.main.common.httpresponsemessage.ResponseMessageType;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorMessage extends BaseMessage {

    private int code;
    private String resource;
    private List<FieldErrorResource> fieldErrors;

    public ErrorMessage() { }

    public ErrorMessage(String summary, String detail, int code) {
        super(ResponseMessageType.ERROR, summary, detail, code);
        this.code = code;
    }

    public int getCode() { return code; }

    public void setCode(int code) { this.code = code; }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public List<FieldErrorResource> getFieldErrors() { return fieldErrors; }

    public void setFieldErrors(List<FieldErrorResource> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}

