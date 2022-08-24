package com.example.personmenegement.services.validation.cheker;

import com.example.personmenegement.api.TaskChecker;
import com.example.personmenegement.dto.TaskDto;
import com.example.personmenegement.services.MessageService;
import com.example.personmenegement.types.Priority;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.personmenegement.types.TaskFieldName.DESCRIPTION;
import static com.example.personmenegement.types.TaskFieldName.PRIORITY;

@Slf4j
public class TaskCheckerImp implements TaskChecker {
    private static final String INCORRECT_PRIORITY = "incorrectPriority";
    private final MessageService messageService = new MessageService();


    public List<String> checkRequiredFields(TaskDto taskDto) {
        log.info("Was calling checkRequiredFields. Input task: {}", taskDto);
        List<String> invalidFields = new ArrayList<>();
        if (taskDto.getDescription() == null || taskDto.getDescription().isBlank()) {
            invalidFields.add(DESCRIPTION);
        }
        if (taskDto.getPriority() == null || taskDto.getPriority().isBlank()) {
            invalidFields.add(PRIORITY);
        }
        return invalidFields;
    }


    public Map<String, String> checkPriority(String priority) {
        log.info("Was calling checkPriority. Input priority: {}", priority);
        Map<String, String> response = new HashMap<>();
        if (Priority.definePriority(priority) == Priority.UNDEFINED) {
            String message = messageService.getMessage(INCORRECT_PRIORITY);
            response.put(PRIORITY, message);
        }
        return response;
    }
}
