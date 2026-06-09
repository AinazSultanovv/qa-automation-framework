package org.qa.models;

public class InsufficientResourcesException extends RuntimeException {
    public InsufficientResourcesException (String message){
        super(message);
    }
}
