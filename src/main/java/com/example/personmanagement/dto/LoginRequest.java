package com.example.personmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
//@ToString(exclude = "password") //todo коммент в коде
@ToString()
public class LoginRequest {
    private String username;
    private String password;
}
