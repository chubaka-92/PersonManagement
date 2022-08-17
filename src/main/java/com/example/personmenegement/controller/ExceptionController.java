package com.example.personmenegement.controller;

import com.example.personmenegement.dto.ErrorDetails;
import com.example.personmenegement.exeption.ManyTasksException;
import com.example.personmenegement.exeption.PersonNotFoundException;
import com.example.personmenegement.exeption.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<?> handlePersonNotFoundException() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<?> handleTaskNotFoundExceptionException() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(ManyTasksException.class)
    public ResponseEntity<?> handleManyTasksExceptionException(ManyTasksException exception, WebRequest request) {
        ErrorDetails errorDetails = getErrorDetails(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    private ErrorDetails getErrorDetails(String exception, Integer statusCode, String request) {
        return ErrorDetails.builder()
                .timeStamp(LocalDate.now())
                .status(statusCode)
                .message(exception)
                .details(request).build();
    }
}