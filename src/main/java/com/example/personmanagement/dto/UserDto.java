package com.example.personmanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
    private String email;
    private Set<String> roles;
    @JsonIgnore
    private boolean valid = true;
}
