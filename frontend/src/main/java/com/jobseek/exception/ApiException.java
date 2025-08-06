package com.jobseek.exception;

public class ApiException extends RuntimeException{
    public ApiException(String message){
        super(message);
    }
}
