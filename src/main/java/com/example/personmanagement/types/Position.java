package com.example.personmanagement.types;

import com.example.personmanagement.services.MessageService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.HashMap;

@Slf4j
@Getter
@ToString
@AllArgsConstructor
public enum Position {

    INTERN(BigDecimal.valueOf(15000), BigDecimal.valueOf(20000), 0.0, 1),
    TECHNOLOGIST(BigDecimal.valueOf(20000), BigDecimal.valueOf(30000), 1.0, 3),
    ENGINEER(BigDecimal.valueOf(30000), BigDecimal.valueOf(40000), 2.0, 6),
    LEAD_ENGINEER(BigDecimal.valueOf(40000), BigDecimal.valueOf(55000), 10.0, 8),
    CHIEF_ENGINEER(BigDecimal.valueOf(55000), BigDecimal.valueOf(65000), 15.0, 10),
    UNDEFINED(null, null, null, null);

    private final BigDecimal salaryMin;
    private final BigDecimal salaryMax;
    private final Double workExperience;
    private final Integer countTasks;
    private static final MessageService messageService = new MessageService();

    public static Position definePosition(String position) {
        log.info("Was calling definePosition. Input position: {}", position);
        return getPositions().getOrDefault(position, UNDEFINED);
    }

    private static HashMap<String, Position> getPositions() {
        HashMap<String, Position> positions = new HashMap<>();
        for (Position ps : Position.values()) {
            positions.put(messageService.getMessage(ps), ps);
        }
        return positions;
    }

}
