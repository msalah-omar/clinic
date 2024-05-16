package com.example.Clinic.rest.exception;

public enum ErrorCodes {
    DUPLICATE_RESOURCE("100", "Resource already exists"),
    RELATED_RESOURCE("200", "Resource has a relations"),
    BUSINESS_VALIDATION("300", "Resource Violates Business Validation");

    private String code;
    private String desc;

    ErrorCodes(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }
}
