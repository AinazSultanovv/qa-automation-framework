package org.qa.models;

public class RecipeNotFoundException extends RuntimeException{
    public RecipeNotFoundException(String message){
        super(message);
    }
}
