package com.example.personmenegement.services.validation.cheker;

import com.example.personmenegement.api.MessageService;
import com.example.personmenegement.api.TaskChecker;
import com.example.personmenegement.dto.TaskDto;
import com.example.personmenegement.types.Priority;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.personmenegement.types.TaskFieldName.DESCRIPTION;
import static com.example.personmenegement.types.TaskFieldName.PRIORITY;

@Slf4j
@RequiredArgsConstructor
public class TaskCheckerImp implements TaskChecker {
    private static final String INCORRECT_PRIORITY = "incorrectPriority";
    private final MessageService messageService;

    // todo выбери один стиль, либо писать руками конструктор, либо lombok
    //  Done

    public List<String> checkRequiredFields(TaskDto taskDto) {
        log.info("Was calling checkRequiredFields. Input task: {}", taskDto);
        List<String> invalidFields = new ArrayList<>();
        if (taskDto.getDescription().isBlank()) {// todo isBlank  //   DONE
            invalidFields.add(DESCRIPTION);
        }
        if (taskDto.getPriority().isBlank()) {// todo isBlank  //   DONE
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
