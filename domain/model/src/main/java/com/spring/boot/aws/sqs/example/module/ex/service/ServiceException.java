package com.spring.boot.aws.sqs.example.module.ex.service;


import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException{

    public static final String DEFAULT_ERROR_CODE = "ERR";

    private String code;

    public ServiceException(String message, String code) {
        super(message);
        this.code = code;
    }

    public ServiceException(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = code;
    }
}
