package com.vaitkevicius.main.common.exceptions;

import com.vaitkevicius.main.common.httpresponsemessage.BaseMessage;
import com.vaitkevicius.main.common.httpresponsemessage.ResponseMessageType;

/**
 * *
 * Created By Povilas 13/11/2018
 * *
 **/
public class BaseException extends RuntimeException {

    private ResponseMessageType severity;
    private String summary;
    private String detail;
    private int errorCode;
    private int httpStatusCode;

    public BaseException() {
    }

    public BaseException(BaseMessage baseMessage) {
        super(baseMessage.getDetail());
        this.severity = baseMessage.getSeverity();
        this.summary = baseMessage.getSummary();
        this.detail = baseMessage.getDetail();
        this.errorCode = baseMessage.getHttpStatusCode();
        this.httpStatusCode = baseMessage.getHttpStatusCode();
    }

    @Override
    public String getMessage() {
        return this.detail;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public ResponseMessageType getSeverity() {
        return severity;
    }

    public String getSummary() {
        return summary;
    }
}
