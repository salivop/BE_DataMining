package com.vaitkevicius.main.common.handlers;

import com.vaitkevicius.main.common.errors.ErrorMessage;
import com.vaitkevicius.main.common.errors.ErrorResponseBody;
import com.vaitkevicius.main.common.errors.FieldErrorResource;
import com.vaitkevicius.main.common.exceptions.InvalidRequestException;
import com.vaitkevicius.main.common.exceptions.ResourceConflictException;
import com.vaitkevicius.main.common.exceptions.ResourceNotFoundException;
import com.vaitkevicius.main.common.utils.NullUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * *
 * Created By Povilas 14/11/2018
 * *
 **/
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messages;

    @ExceptionHandler(value = { ResourceConflictException.class })
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        ResourceConflictException exception = (ResourceConflictException) ex;
        ErrorResponseBody body = new ErrorResponseBody();
        body.setSeverity(exception.getSeverity());
        body.setSummary(exception.getSummary());
        body.setDetail(exception.getMessage());
        body.setErrorCode(exception.getErrorCode());
        body.setHttpStatusCode(exception.getHttpStatusCode());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(ex, body, headers, HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = { ResourceNotFoundException.class })
    protected ResponseEntity<Object> handleResourceNotFound(RuntimeException ex, WebRequest request) {
        ResourceNotFoundException exception = (ResourceNotFoundException) ex;
        ErrorResponseBody body = new ErrorResponseBody();
        body.setSeverity(exception.getSeverity());
        body.setSummary(exception.getSummary());
        body.setDetail(exception.getMessage());
        body.setErrorCode(exception.getErrorCode());
        body.setHttpStatusCode(exception.getHttpStatusCode());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(ex, body, headers, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ InvalidRequestException.class })
    protected ResponseEntity<Object> handleInvalidRequest(RuntimeException e, WebRequest request) {
        InvalidRequestException enf = (InvalidRequestException) e;
        List<FieldErrorResource> fieldErrorResources = new ArrayList<>();
        List<FieldError> fieldErrors = NullUtils.call(enf.getErrors(), Errors::getFieldErrors);
        for (FieldError fieldError : CollectionUtils.emptyIfNull(fieldErrors)) {
            FieldErrorResource fieldErrorResource = new FieldErrorResource();
            fieldErrorResource.setField(fieldError.getField());
            fieldErrorResource.setCode(fieldError.getCode());
            fieldErrorResource.setMessage(fieldError.getDefaultMessage());
            fieldErrorResources.add(fieldErrorResource);
        }
        ErrorMessage error = new ErrorMessage(
                messages.getMessage("message.severity.error", null, LocaleContextHolder.getLocale()),
                enf.getMessage(),
                HttpStatus.UNPROCESSABLE_ENTITY.value());
        error.setResource(NullUtils.call(enf.getErrors(), Errors::getObjectName));
        error.setFieldErrors(fieldErrorResources);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(e, error, headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

//    @ExceptionHandler({ InvalidIntegrationRequestException.class })
//    protected ResponseEntity<Object> handleIntegrationInvalidRequest(RuntimeException e, WebRequest request) {
//        InvalidIntegrationRequestException enf = (InvalidIntegrationRequestException) e;
//        List<FieldErrorResource> fieldErrorResources = new ArrayList<>();
//        List<FieldError> fieldErrors = NullUtils.call(enf.getErrors(), Errors::getFieldErrors);
//        for (FieldError fieldError : CollectionUtils.emptyIfNull(fieldErrors)) {
//            FieldErrorResource fieldErrorResource = new FieldErrorResource();
//            fieldErrorResource.setField(fieldError.getField());
//            fieldErrorResource.setCode(fieldError.getCode());
//            fieldErrorResource.setMessage(fieldError.getDefaultMessage());
//            fieldErrorResources.add(fieldErrorResource);
//        }
//        ErrorMessage error = new ErrorMessage(
//                messages.getMessage("message.severity.error", null, LocaleContextHolder.getLocale()),
//                enf.getMessage(),
//                HttpStatus.UNPROCESSABLE_ENTITY.value());
//        error.setResource(NullUtils.call(enf.getErrors(), Errors::getObjectName));
//        error.setFieldErrors(fieldErrorResources);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        return handleExceptionInternal(e, error, headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
//    }
}

