package com.example.personmanagement.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
    private LocalDate timeStamp;
    private Integer status;
    private String message;
    private String details;
}
