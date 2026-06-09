package org.qa.models;

public class LevelTooLowException extends RuntimeException{
    public LevelTooLowException(String message){
        super(message);
    }
}
