package com.example.personmenegement.exeption;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(String description) {
        super(description);
    }
}
