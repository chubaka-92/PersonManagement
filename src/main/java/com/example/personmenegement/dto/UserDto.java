package com.example.personmenegement.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "password")
public class UserDto {
    private String username;
    private String email;
    private Set<String> roles;
    private String password;
}
