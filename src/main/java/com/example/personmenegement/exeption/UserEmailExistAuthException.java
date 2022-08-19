package com.example.personmenegement.exeption;

public class UserEmailExistAuthException extends RuntimeException{
    public UserEmailExistAuthException(String description){
        super(description);
    }
}