package com.example.personmenegement.exeption;

public class ManyTasksException extends RuntimeException {
    public ManyTasksException(String description){
        super(description);
    }
}
