package com.example.personmenegement.exeption;

public class UserNameExistAuthException extends RuntimeException{
    public UserNameExistAuthException(String description){
        super(description);
    }
}
