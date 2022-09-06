package com.example.personmanagement.services.validation.validator;

import com.example.personmanagement.api.TaskChecker;
import com.example.personmanagement.api.TaskInitializer;
import com.example.personmanagement.api.TaskValidation;
import com.example.personmanagement.dto.TaskDto;
import com.example.personmanagement.services.validation.cheker.TaskCheckerImp;
import com.example.personmanagement.services.validation.initializer.TaskInitializerImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TaskValidationImp implements TaskValidation {

    public TaskDto validate(TaskDto taskDto) {
        log.info("Was calling validate. Input task: " + taskDto);
        TaskChecker taskChecker = new TaskCheckerImp();
        TaskInitializer taskErrorMessage = new TaskInitializerImp(taskDto);

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
