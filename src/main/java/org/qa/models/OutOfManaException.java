package org.qa.models;

public class OutOfManaException extends RuntimeException{
    public OutOfManaException(String message){
        super(message);
    }
}