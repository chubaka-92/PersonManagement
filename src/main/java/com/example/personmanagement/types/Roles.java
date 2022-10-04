package com.example.personmanagement.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Slf4j
@Getter
@ToString
@AllArgsConstructor
public enum Roles {
    ROLE_USER("user"),
    ROLE_MODERATOR("moderator"),
    ROLE_ADMIN("admin"),
    UNDEFINED("undefined");

    private final String name;

    public static Roles defineRole(String role) {
        log.info("Was calling defineRole. Input role: {}", role);
        return defineRole().getOrDefault(role, UNDEFINED);
    }

    private static HashMap<String, Roles> defineRole() { //todo defineRole  //  DONE
        HashMap<String, Roles> roles = new HashMap<>();
        for (Roles role : Roles.values()) { //todo не сокращай переменные  //  DONE
            roles.put(role.name, role);
        }
        return roles;
    }
}
