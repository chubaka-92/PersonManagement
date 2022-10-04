package com.example.personmanagement.types;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.ResourceBundle;

@Slf4j
@ToString
public enum Priority {
    LOW,
    MEDIUM,
    HIGH,
    CRITICAL,
    UNDEFINED;

    private static final String MESSAGE = "message";

    public static Priority definePriority(String priority) {
        log.info("Was calling definePriority. Input priority: {}", priority);
        return definePriorities().getOrDefault(priority, UNDEFINED);
    }

    private static HashMap<String, Priority> definePriorities() { //todo definePriorities  //  DONE
        HashMap<String, Priority> priorities = new HashMap<>();
        for (Priority priority : Priority.values()) { //todo pr - сокращай  //  DONE
            priorities.put(ResourceBundle.getBundle(MESSAGE).getString(priority.name()), priority);
        }
        return priorities;
    }
}
