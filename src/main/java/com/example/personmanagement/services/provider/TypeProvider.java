package com.example.personmanagement.services.provider;

import com.example.personmanagement.services.MessageService;
import com.example.personmanagement.types.Position;
import com.example.personmanagement.types.Priority;
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
public class TypeProvider {
    private final MessageService messageService;
    private final Map<String, Position> positions = new HashMap<>();
    private final Map<String, Priority> priorities = new HashMap<>();

    @PostConstruct
    public void init() {
        for (Priority priority : Priority.values()) {
            priorities.put(messageService.getMessage(priority), priority);
        }
        for (Position position : Position.values()) {
            positions.put(messageService.getMessage(position), position);
        }
    }

    public Priority getPriority(String priority) {
        log.debug("Was calling getPriority.");
        return priorities.getOrDefault(priority, Priority.UNDEFINED);
    }

    public Position getPosition(String position) {
        log.debug("Was calling getPosition.");
        return positions.getOrDefault(position, Position.UNDEFINED);
    }
}
