package com.vaitkevicius.main.common.utils;

import com.vaitkevicius.main.common.exceptions.InvalidRequestException;
import com.vaitkevicius.main.common.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

/**
 * *
 * Created By Povilas 13/11/2018
 * *
 **/
@Service
public class ExceptionFactory {

    @Autowired
    private MessageUtils messageUtils;

    public ResourceNotFoundException getResourseNotFoundException(String messageCode, Object... args) {
        return new ResourceNotFoundException(
                messageUtils.getMessage("message.severity.error"),
                messageUtils.getMessage(messageCode, args)
        );
    }

    public InvalidRequestException getInvalidRequestException(
            String messageCode,
            String defaultMessageCode,
            Errors errors,
            Object... args
    ) {
        return new InvalidRequestException(
                messageUtils.getMessage(messageCode == null ? defaultMessageCode : messageCode, args),
                errors

        );
    }
}
