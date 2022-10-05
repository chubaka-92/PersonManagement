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
public class Positions {

    private final MessageService messageService;
    private final Map<String, Position> positions = new HashMap<>();

    @PostConstruct
    public void init() {
        for (Position position : Position.values()) {
            positions.put(messageService.getMessage(position), position);
        }
    }

    public Position getPosition(String position) {
        log.debug("Was calling getPosition.");
        return positions.getOrDefault(position, Position.UNDEFINED);
    }
}
