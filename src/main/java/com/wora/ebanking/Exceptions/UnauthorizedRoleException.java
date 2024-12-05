package com.wora.ebanking.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedRoleException extends RuntimeException{
    public UnauthorizedRoleException(String message){
        super(message);
    }
}
