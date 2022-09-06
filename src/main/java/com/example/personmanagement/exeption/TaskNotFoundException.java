package com.example.personmanagement.exeption;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String description) {
        super(description);
    }
}
