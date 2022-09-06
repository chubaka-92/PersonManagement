package com.example.personmanagement.types;

import com.example.personmanagement.services.MessageService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

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

    public static Position definePosition(String position) {
        log.info("Was calling definePosition. Input position: {}", position);
        MessageService messageService = new MessageService();
        if (messageService.getMessage(INTERN).equals(position)) {
            return INTERN;
        } else if (messageService.getMessage(TECHNOLOGIST).equals(position)) {
            return TECHNOLOGIST;
        } else if (messageService.getMessage(ENGINEER).equals(position)) {
            return ENGINEER;
        } else if (messageService.getMessage(LEAD_ENGINEER).equals(position)) {
            return LEAD_ENGINEER;
        } else if (messageService.getMessage(CHIEF_ENGINEER).equals(position)) {
            return CHIEF_ENGINEER;
        } else {
            return UNDEFINED;
        }
    }
}
