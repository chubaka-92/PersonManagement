package com.example.personmenegement.services.validation.initializer;

import com.example.personmenegement.api.TaskInitializer;
import com.example.personmenegement.dto.TaskDto;
import com.example.personmenegement.services.MessageService;
import com.example.personmenegement.types.TaskFieldName;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
@Getter
@RequiredArgsConstructor
public class TaskInitializerImp implements TaskInitializer {
    private static final String EMPTY_FIELD = "emptyField";
    private final TaskDto taskDtoError;
    private final MessageService messageService = new MessageService();


    public void addFieldsEmpty(List<String> incorrectFields) {
        log.info("Was calling addFieldsEmpty. Input incorrectFields: " + incorrectFields);
        if (!incorrectFields.isEmpty()) {
            taskDtoError.setValid(false);
        }
        incorrectFields.forEach(this::settingEmptyField);
    }

    public void addIncorrectArgumentMessage(Map<String, String> incorrectArguments) {
        log.info("Was calling addIncorrectArgumentMessage. Input incorrectArguments: " + incorrectArguments);
        if (!incorrectArguments.isEmpty()) {
            taskDtoError.setValid(false);
        }
        incorrectArguments.entrySet().forEach(this::settingIncorrectArgumentMessage);
    }

    public boolean hasErrors() {
        log.info("Was calling hasErrors.");
        return !taskDtoError.isValid();
    }

    private void settingEmptyField(String field) {
        log.debug("Was calling settingEmptyField. Input field: " + field);
        switch (field) {
            case TaskFieldName.DESCRIPTION: {
                taskDtoError.setDescription(messageService.getMessage(EMPTY_FIELD));
                break;
            }
            case TaskFieldName.PRIORITY: {
                taskDtoError.setPriority(messageService.getMessage(EMPTY_FIELD));
                break;
            }
            default:
                break;
        }
    }

    private void settingIncorrectArgumentMessage(Map.Entry<String, String> entry) {
        log.debug("Was calling settingIncorrectArgumentMessage. Input entry: " + entry);
        switch (entry.getKey()) {
            case TaskFieldName.DESCRIPTION: {
                taskDtoError.setDescription(entry.getValue());
                break;
            }
            case TaskFieldName.PRIORITY: {
                taskDtoError.setPriority(entry.getValue());
                break;
            }
            default:
                break;
        }
    }
}