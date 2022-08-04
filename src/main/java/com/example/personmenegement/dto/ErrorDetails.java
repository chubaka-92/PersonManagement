package com.example.personmenegement.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class ErrorDetails {
    private LocalDate timeStamp;
    private int status;
    private String message;
    private String details;
}
