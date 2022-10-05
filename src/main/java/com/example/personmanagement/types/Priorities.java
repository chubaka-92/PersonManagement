package com.example.personmanagement.types;

import com.example.personmanagement.services.MessageService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Getter
@Component
@RequiredArgsConstructor
public class Priorities {
    private final MessageService messageService;
    private final Map<String, Priority> priorities = new HashMap<>();

    @PostConstruct
    public void init() {
        for (Priority priority : Priority.values()) {
            priorities.put(messageService.getMessage(priority), priority);
        }
    }

    public Priority getPriority(String priority) {
        log.debug("Was calling getPriority.");
        return priorities.getOrDefault(priority, Priority.UNDEFINED);
    }
}
