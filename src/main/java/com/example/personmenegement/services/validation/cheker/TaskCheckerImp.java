package com.example.personmenegement.services.validation.cheker;

import com.example.personmenegement.api.ResourceBundleService;
import com.example.personmenegement.api.TaskChecker;
import com.example.personmenegement.dto.Person;
import com.example.personmenegement.dto.Task;
import com.example.personmenegement.entity.PersonEntity;
import com.example.personmenegement.services.ResourceBundleServiceImp;
import com.example.personmenegement.types.Position;
import com.example.personmenegement.types.Priority;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.personmenegement.types.PersonFieldName.POSITION;
import static com.example.personmenegement.types.TaskFieldName.*;
import static com.example.personmenegement.types.Position.checkExperienceMatchingPosition;

@Slf4j
public class TaskCheckerImp implements TaskChecker {
    private static final String INCORRECT_PRIORITY = "incorrectPriority";
    private final ResourceBundleService messageService;

    public TaskCheckerImp() {
        this.messageService = new ResourceBundleServiceImp();
    }

    public List<String> checkRequiredFields(Task task) {
        log.info("Was calling checkRequiredFields. Input task: " + task.toString());
        List<String> invalidFields = new ArrayList<>();
        if (task.getDescription() == null || task.getDescription().trim().equals("")) {
            invalidFields.add(DESCRIPTION);
        }
        if (task.getPriority() == null || task.getPriority().equals("")) {
            invalidFields.add(PRIORITY);
        }
        return invalidFields;
    }


    public Map<String, String> checkPriority(String priority) {
        log.info("Was calling checkPriority. Input priority: " + priority);
        Map<String, String> response = new HashMap<>();
        if (Priority.getDefine(priority) == Priority.UNDEFINED) {
            String message = messageService.getString(INCORRECT_PRIORITY);
            response.put(PRIORITY, message);
        }
        return response;
    }
}
