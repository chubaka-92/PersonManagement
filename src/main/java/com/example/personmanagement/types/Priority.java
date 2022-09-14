package com.example.personmanagement.types;

import com.example.personmanagement.services.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Slf4j
@ToString
public enum Priority {
    LOW,
    MEDIUM,
    HIGH,
    CRITICAL,
    UNDEFINED;

    private static final MessageService messageService = new MessageService();

    public static Priority definePriority(String priority) {
        log.info("Was calling definePriority. Input priority: {}", priority);
        return getPriorities().getOrDefault(priority, UNDEFINED);
    }

    private static HashMap<String, Priority> getPriorities() { //todo definePriorities
        HashMap<String, Priority> priorities = new HashMap<>();
        for (Priority pr : Priority.values()) { //todo pr - сокращай
            priorities.put(messageService.getMessage(pr), pr);
        }
        return priorities;
    }
}
