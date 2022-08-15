package com.example.personmenegement.types;

import com.example.personmenegement.api.MessageService;
import com.example.personmenegement.services.MessageServiceImp;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
@AllArgsConstructor
public enum Priority {
    LOW,
    MEDIUM,
    HIGH,
    CRITICAL,
    UNDEFINED;


    public static Priority definePriority(String priority) {
        log.info("Was calling definePriority. Input priority: {}", priority);
        MessageService messageService = new MessageServiceImp();
        if (messageService.getMessage(LOW).equals(priority)) {
            return LOW;
        } else if (messageService.getMessage(MEDIUM).equals(priority)) {
            return MEDIUM;
        } else if (messageService.getMessage(HIGH).equals(priority)) {
            return HIGH;
        } else if (messageService.getMessage(CRITICAL).equals(priority)) {
            return CRITICAL;
        } else {
            return UNDEFINED;
        }
    }
}
