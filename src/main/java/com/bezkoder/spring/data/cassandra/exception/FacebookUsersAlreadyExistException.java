package com.bezkoder.spring.data.cassandra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class FacebookUsersAlreadyExistException extends RuntimeException {

    public FacebookUsersAlreadyExistException(){
        super();
    }

    public FacebookUsersAlreadyExistException(String message,Throwable cause){
        super(message, cause);
    }

    public FacebookUsersAlreadyExistException(String message){
        super(message);
    }

    public FacebookUsersAlreadyExistException(Throwable cause){
        super(cause);
    }
}
