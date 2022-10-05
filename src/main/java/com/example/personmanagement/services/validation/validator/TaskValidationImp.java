package com.example.personmanagement.services.validation.validator;

import com.example.personmanagement.api.checker.TaskChecker;
import com.example.personmanagement.api.initializer.TaskInitializer;
import com.example.personmanagement.api.validation.TaskValidation;
import com.example.personmanagement.dto.TaskDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskValidationImp implements TaskValidation {

    private final TaskChecker taskChecker;
    private final TaskInitializer taskErrorMessage;

    public TaskDto validate(TaskDto taskDto) {
        log.info("Was calling validate. Input task: {}", taskDto);

        taskErrorMessage.setTaskDtoError(taskDto);
        taskErrorMessage.addFieldsEmpty(taskChecker.checkRequiredFields(taskDto));

        if (!taskErrorMessage.hasErrors()) {
            taskErrorMessage.addIncorrectArgumentMessage(taskChecker.checkPriority(taskDto.getPriority()));
        }

        if (taskErrorMessage.hasErrors()) {
            return taskErrorMessage.getTaskDtoError();
        }
        return null;
    }
}
