package com.vaitkevicius.main.common.exceptions;

import org.springframework.validation.Errors;

/**
 * *
 * Created By Povilas 13/11/2018
 * *
 **/
public class InvalidRequestException extends RuntimeException {

    private static final long serialVersionUID = 7709985476948149020L;

    private Errors errors;

    public InvalidRequestException(String message, Errors errors) {
        super(message);
        this.errors = errors;
    }

    public Errors getErrors() {
        return errors;
    }
}
