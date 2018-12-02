package com.vaitkevicius.main.common.exceptions;

import com.vaitkevicius.main.common.httpresponsemessage.BaseMessage;
import com.vaitkevicius.main.common.httpresponsemessage.ResponseMessageType;
import org.springframework.http.HttpStatus;

/**
 * *
 * Created By Povilas 15/11/2018
 * *
 **/
public class ResourceConflictException extends BaseException {

    private static final long serialVersionUID = 8029959100877140435L;

    public ResourceConflictException(String detail) {
        super(new BaseMessage(
                ResponseMessageType.ERROR,
                null,
                detail,
                HttpStatus.CONFLICT.value()
        ));
    }

    public ResourceConflictException(String summary, String detail) {
        super(new BaseMessage(
                ResponseMessageType.ERROR,
                summary,
                detail,
                HttpStatus.CONFLICT.value()
        ));
    }

    public ResourceConflictException(ResponseMessageType severity, String detail) {
        super(new BaseMessage(
                severity,
                null,
                detail,
                HttpStatus.CONFLICT.value()
        ));
    }

    public ResourceConflictException(ResponseMessageType severity, String summary, String detail, int httpStatusCode) {
        super(new BaseMessage(
                severity,
                summary,
                detail,
                httpStatusCode
        ));
    }
}
