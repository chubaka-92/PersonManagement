package com.example.personmanagement.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JwtResponse {
    private Long id;
    private String token;
    private String type;
    private String username;
    private String email;
    private List<String> roles;
}