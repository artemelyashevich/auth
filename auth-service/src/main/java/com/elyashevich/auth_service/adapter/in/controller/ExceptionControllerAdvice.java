package com.elyashevich.auth_service.adapter.in.controller;

import com.elyashevich.auth_service.adapter.in.dto.ExceptionBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    private static final String NOT_SUPPORTED_MESSAGE = "Http method with this URL not found.";
    private static final String FAILED_VALIDATION_MESSAGE = "Validation failed.";
    private static final String UNEXPECTED_ERROR_MESSAGE = "Something went wrong.";

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ExceptionBody handleException(final HttpRequestMethodNotSupportedException exception) {
        return this.handleException(exception, NOT_SUPPORTED_MESSAGE);
    }

    @SuppressWarnings("all")
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleValidation(final MethodArgumentNotValidException exception) {
        var errors = exception.getBindingResult()
                .getFieldErrors().stream()
                .collect(Collectors.toMap(
                                FieldError::getField,
                                fieldError -> fieldError.getDefaultMessage(),
                                (exist, newMessage) -> exist + " " + newMessage
                        )
                );
        return new ExceptionBody(FAILED_VALIDATION_MESSAGE, errors);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ExceptionBody handleException(final Exception exception) {
        return this.handleException(exception, UNEXPECTED_ERROR_MESSAGE);
    }

    private ExceptionBody handleException(final Exception exception, final String defaultMessage) {
        var message = exception.getMessage() == null ? defaultMessage : exception.getMessage();
        log.warn("{} '{}'.", defaultMessage, message);
        return new ExceptionBody(message);
    }
}