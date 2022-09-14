package com.example.personmanagement.types;

import com.example.personmanagement.services.MessageService;
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

    private static final MessageService messageService = new MessageService();

    public static Roles defineRole(String role) {
        log.info("Was calling defineRole. Input role: {}", role);
        return getRoles().getOrDefault(role, UNDEFINED);
    }

    private static HashMap<String, Roles> getRoles() { //todo defineRole
        HashMap<String, Roles> roles = new HashMap<>();
        for (Roles rl : Roles.values()) { //todo не сокращай переменные
            roles.put(messageService.getMessage(rl), rl);
        }
        return roles;
    }
}
