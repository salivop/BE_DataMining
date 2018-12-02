package com.vaitkevicius.main.common.validation.validators;

import com.vaitkevicius.main.common.utils.ExceptionFactory;
import com.vaitkevicius.main.common.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;

/**
 * *
 * Created By Povilas 13/11/2018
 * *
 **/
public class ExceptionAwareValidator {

    protected static final String DO = "do";
    protected static final String GET = "get";
    protected static final String SAVE = "save";
    protected static final String UPDATE = "update";
    protected static final String DELETE = "delete";
    protected static final String UNPROCESSABLE_ENTITY_CODE = String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY.value());
    protected static final long DAY = 1000 * 60 * 60 * 23;

    @Autowired
    private MessageUtils messageUtils;

    @Autowired
    private ExceptionFactory exceptionFactory;

    public void throwException(Errors errors, String operation) {
        throwException(errors, operation, null);
    }

    public void throwException(Errors errors, String operation, String messageCode, Object... messageArgs) {
        if (operation.equals(DO)) {
            throw exceptionFactory.getInvalidRequestException(messageCode, "message.error.do.invalidRequest", errors, messageArgs);
        }
        if (operation.equals(GET)) {
            throw exceptionFactory.getInvalidRequestException(messageCode, "message.error.get.invalidRequest", errors, messageArgs);
        }
        if (operation.equals(SAVE)) {
            throw exceptionFactory.getInvalidRequestException(messageCode, "message.error.save.invalidRequest", errors, messageArgs);
        }
        if (operation.equals(UPDATE)) {
            throw exceptionFactory.getInvalidRequestException(messageCode, "message.error.update.invalidRequest", errors, messageArgs);
        }
        if (operation.equals(DELETE)) {
            throw exceptionFactory.getInvalidRequestException(messageCode, "message.error.delete.invalidRequest", errors, messageArgs);
        }
    }

    public void throwIfHaveErrors(Errors errors, String operation) {
        if (errors.hasErrors()) {
            throwException(errors, operation);
        }
    }

    public void throwResourceNotFound(String messageCode, Object... args) {
        throw exceptionFactory.getResourseNotFoundException(messageCode, args);
    }

    public void rejectValue(Errors errors, String field, String messageCode, Object... args) {
        errors.rejectValue(field, UNPROCESSABLE_ENTITY_CODE, messageUtils.getMessage(messageCode, args));
    }
}
