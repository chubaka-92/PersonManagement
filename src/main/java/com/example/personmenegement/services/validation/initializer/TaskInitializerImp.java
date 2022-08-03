package com.example.personmenegement.services.validation.initializer;

import com.example.personmenegement.api.MessageService;
import com.example.personmenegement.api.TaskInitializer;
import com.example.personmenegement.dto.Task;
import com.example.personmenegement.services.MessageServiceImp;
import com.example.personmenegement.types.TaskFieldName;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
@Getter
public class TaskInitializerImp implements TaskInitializer {
    private static final String EMPTY_FIELD = "emptyField";

    private final Task taskError;
    private final MessageService messageService;

    // todo выбери один стиль, либо писать руками конструктор, либо lombok
    public TaskInitializerImp(Task task) {
        this.taskError = task;
        this.messageService = new MessageServiceImp();
    }

    public void addFieldsEmpty(List<String> incorrectFields) {
        log.info("Was calling addFieldsEmpty. Input incorrectFields: " + incorrectFields.toString());
        if (!incorrectFields.isEmpty()) {
            taskError.setValid(false);
        }
        for (String field : incorrectFields) {
            switch (field) {
                case TaskFieldName.DESCRIPTION: {
                    taskError.setDescription(messageService.getMessage(EMPTY_FIELD));
                    break;
                }
                case TaskFieldName.PRIORITY: {
                    taskError.setPriority(messageService.getMessage(EMPTY_FIELD));
                    break;
                }
                default:
                    break;
            }
        }
    }

    public void addIncorrectArgumentMessage(Map<String, String> incorrectArguments) {
        log.info("Was calling addIncorrectArgumentMessage. Input incorrectArguments: " + incorrectArguments.toString());
        if (!incorrectArguments.isEmpty()) {
            taskError.setValid(false);
        }
        for (Map.Entry<String, String> entry : incorrectArguments.entrySet()) {
            switch (entry.getKey()) {
                case TaskFieldName.DESCRIPTION: {
                    taskError.setDescription(entry.getValue());
                    break;
                }
                case TaskFieldName.PRIORITY: {
                    taskError.setPriority(entry.getValue());
                    break;
                }
                default:
                    break;
            }
        }
    }

    public boolean hasErrors() {
        log.info("Was calling hasErrors.");
        return !taskError.isValid();
    }
}
