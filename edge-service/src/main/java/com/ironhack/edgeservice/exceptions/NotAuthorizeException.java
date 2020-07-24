package com.ironhack.edgeservice.exceptions;

public class NotAuthorizeException extends RuntimeException{
    public NotAuthorizeException(String message){
        super(message);
    }
}
