package com.example.personmenegement.exeption;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String description) {
        super(description);
    }
}
