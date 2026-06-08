package org.qa.models;

public class CharacterIsDeadException extends RuntimeException{
    public CharacterIsDeadException(String message){
        super(message);
    }
}