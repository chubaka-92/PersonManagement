package com.example.personmenegement.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@ToString
@AllArgsConstructor
public enum Roles {
    ROLE_USER("user"),
    ROLE_MODERATOR("moderator"),
    ROLE_ADMIN("admin"),
    UNDEFINED("undefined");

    private final String name;// todo можно обойтись без него, у тебя в сущности есть @Enumerated

    public static Roles defineRole(String role) {
        log.info("Was calling defineRole. Input role: {}", role);
        if (ROLE_USER.name.equals(role)) {
            return ROLE_USER;
        } else if (ROLE_MODERATOR.name.equals(role)) {
            return ROLE_MODERATOR;
        } else if (ROLE_ADMIN.name.equals(role)) {
            return ROLE_ADMIN;
        } else {
            return UNDEFINED;
        }
    }
}
