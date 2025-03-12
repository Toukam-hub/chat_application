package com.neambo.demowebsocket.exception;

public class ResourceAlreadyExisteException extends RuntimeException{
    public ResourceAlreadyExisteException(String message){
        super(message);
    }
}
