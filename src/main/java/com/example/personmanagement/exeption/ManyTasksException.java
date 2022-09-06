package com.example.personmanagement.exeption;

public class ManyTasksException extends RuntimeException {
    public ManyTasksException(String description) {
        super(description);
    }
}
