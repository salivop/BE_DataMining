package com.vaitkevicius.main.common.httpresponsemessage;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;

import java.util.Map;

/**
 * *
 * Created By Povilas 12/11/2018
 * *
 **/
public class ResponseMessage extends BaseMessage {

    private Map<String, Object> attributes = new ModelMap();

    public ResponseMessage() {
    }

    public ResponseMessage(ResponseMessageType severity, String summary, String detail, int httpStatusCode) {
        super(severity, summary, detail, httpStatusCode);
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public static final ResponseMessage getSuccess(MessageSource messages, String successCode) {
        return new ResponseMessage(
                ResponseMessageType.SUCCESS,
                messages.getMessage("message.severity.success", null, LocaleContextHolder.getLocale()),
                messages.getMessage(successCode, null, LocaleContextHolder.getLocale()),
                HttpStatus.OK.value());
    }
}
