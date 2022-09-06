package com.example.personmanagement.exeption;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(String description) {
        super(description);
    }
}
