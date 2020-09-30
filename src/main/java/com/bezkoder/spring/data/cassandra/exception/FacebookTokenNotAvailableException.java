package com.bezkoder.spring.data.cassandra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class FacebookTokenNotAvailableException extends RuntimeException {

    public FacebookTokenNotAvailableException(){
        super();
    }

    public FacebookTokenNotAvailableException(String message, Throwable cause){
        super(message, cause);
    }

    public FacebookTokenNotAvailableException(String message){
        super(message);
    }

    public FacebookTokenNotAvailableException(Throwable cause){
        super(cause);
    }

}
