package com.bezkoder.spring.data.cassandra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FacebookTokenNotFoundException extends RuntimeException {

    public FacebookTokenNotFoundException(){
        super();
    }

    public FacebookTokenNotFoundException(String message,Throwable cause){
        super(message, cause);
    }

    public FacebookTokenNotFoundException(String message){
        super(message);
    }

    public FacebookTokenNotFoundException(Throwable cause){
        super(cause);
    }
}
