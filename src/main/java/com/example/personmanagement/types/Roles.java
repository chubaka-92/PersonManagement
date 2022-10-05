package com.example.personmanagement.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

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
    private static final Map<String, Roles> ROLES = new HashMap<>();

    static {
        for (Roles role : Roles.values()) {
            ROLES.put(role.name, role);
        }
    }

    public static Roles defineRole(String role) {
        log.info("Was calling defineRole. Input role: {}", role);
        return ROLES.getOrDefault(role, UNDEFINED);
    }
}
