package com.example.Clinic.rest.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler {

    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, LocalDateTime.now(), "Malformed JSON request", details);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());

        StringBuilder errorMessageBuilder = new StringBuilder();
        errorMessageBuilder.append(ex.getMethod());
        errorMessageBuilder.append(
                " method is not supported for this request. Supported methods are ");
        ex.getSupportedHttpMethods().forEach(errorMessageBuilder::append);

        ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED, LocalDateTime.now(),
                errorMessageBuilder.toString(), details);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    protected ResponseEntity<Object> handleServletRequestBindingException(
            ServletRequestBindingException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, LocalDateTime.now(), "Missing Parameters",
                details);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getObjectName() + " : " + error.getDefaultMessage())
                .collect(Collectors.toList());

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, LocalDateTime.now(), "Validation Errors", details);

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    //custom exception handling start from here

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFoundException(
            ResourceNotFoundException ex) {

        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());

        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, LocalDateTime.now(), "Resource Not Found", details);

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    protected ResponseEntity<Object> handleResourceAlreadyExistsException(
            ResourceAlreadyExistsException ex) {

        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());

        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getCode(), LocalDateTime.now(), ErrorCodes.DUPLICATE_RESOURCE.getDesc(), details);

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(ResourceRelatedException.class)
    protected ResponseEntity<Object> handleResourceRelatedException(
            ResourceRelatedException ex) {

        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());

        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getCode(), LocalDateTime.now(), ErrorCodes.RELATED_RESOURCE.getDesc(), details);

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(BusinessValidationException.class)
    protected ResponseEntity<Object> handleBusinessValidationException(
            BusinessValidationException ex) {

        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());

        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getCode(), LocalDateTime.now(), ErrorCodes.BUSINESS_VALIDATION.getDesc(), details);

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<Object> handleBadRequestException(
            BadRequestException ex) {

        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, null , LocalDateTime.now(), BadRequestException.class.getSimpleName() , details);

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
