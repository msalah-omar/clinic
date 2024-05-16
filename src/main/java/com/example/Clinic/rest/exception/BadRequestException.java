package com.example.Clinic.rest.exception;


public class BadRequestException extends RuntimeException {

    public BadRequestException(String msg) {
        super(msg);
    }
}