package com.vaitkevicius.main.common.errors;

import com.vaitkevicius.main.common.httpresponsemessage.BaseMessage;

/**
 * *
 * Created By Povilas 14/11/2018
 * *
 **/
public class ErrorResponseBody extends BaseMessage {

    private int errorCode;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

}
