package com.bezkoder.spring.data.cassandra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class FacebookTokenAlreadyExistException extends RuntimeException {

    public FacebookTokenAlreadyExistException(){
        super();
    }

    public FacebookTokenAlreadyExistException(String message,Throwable cause){
        super(message, cause);
    }

    public FacebookTokenAlreadyExistException(String message){
        super(message);
    }

    public FacebookTokenAlreadyExistException(Throwable cause){
        super(cause);
    }
}
