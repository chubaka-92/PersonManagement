package com.example.personmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
//todo коммент в коде
//  DONE
@ToString()
public class LoginRequest {
    private String username;
    private String password;
}
