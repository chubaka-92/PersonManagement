package com.example.personmenegement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "password")
public class LoginRequest {
    private String username;
    private String password;
}
