package com.vaitkevicius.main.common.httpresponsemessage;

import lombok.Getter;
import lombok.Setter;

/**
 * *
 * Created By Povilas 12/11/2018
 * *
 **/
@Getter
@Setter
public class BaseMessage {

    private ResponseMessageType severity;
    private String summary;
    private String detail;
    private int httpStatusCode;

    public BaseMessage() {
    }

    public BaseMessage(String detail) { this.detail = detail; }

    public BaseMessage(int code, String detail){
        this.httpStatusCode = code;
        this.detail = detail;
    }

    public BaseMessage(ResponseMessageType severity, String summary, String detail, int httpStatusCode){
        this.severity = severity;
        this.summary = summary;
        this.detail = detail;
        this.httpStatusCode = httpStatusCode;

    }

}
