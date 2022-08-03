package com.example.personmenegement.services.validation.validator;

import com.example.personmenegement.api.TaskChecker;
import com.example.personmenegement.api.TaskInitializer;
import com.example.personmenegement.api.TaskValidation;
import com.example.personmenegement.dto.Task;
import com.example.personmenegement.services.validation.cheker.TaskCheckerImp;
import com.example.personmenegement.services.validation.initializer.TaskInitializerImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class TaskValidationImp  implements TaskValidation {

    public Task validate(Task task) {
        log.info("Was calling validate. Input task: " + task.toString());
        TaskChecker taskChecker = new TaskCheckerImp();
        TaskInitializer taskErrorMessage = new TaskInitializerImp(task);

        taskErrorMessage.addFieldsEmpty(taskChecker.checkRequiredFields(task));

        if (!taskErrorMessage.hasErrors()) {
                taskErrorMessage.addIncorrectArgumentMessage(taskChecker.checkPriority(task.getPriority()));
        }

        if (taskErrorMessage.hasErrors()) {
            return ((TaskInitializerImp) taskErrorMessage).getTaskError();// todo так нельзя, лучше добавь этот метод в интерфейс
        }
        return null;
    }
}
