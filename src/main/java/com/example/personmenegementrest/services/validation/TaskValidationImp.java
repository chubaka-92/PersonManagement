package com.example.personmenegementrest.services.validation;

import com.example.personmenegementrest.api.TaskChecker;
import com.example.personmenegementrest.api.TaskValidation;
import com.example.personmenegementrest.dto.Task;
import com.example.personmenegementrest.entity.PersonEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskValidationImp implements TaskValidation {

    private final TaskChecker taskChecker;

    @Override
    public void addTaskValidator(Task task) {
        taskChecker.checkTaskRequiredFields(task);
    }

    @Override
    public void updateTaskValidator(Task task) {
        taskChecker.checkTaskRequiredFields(task);
    }

    @Override
    public boolean availableCountTasksToPersonValidator(List<Task> tasks, PersonEntity personEntity) {
        return taskChecker.checkAvailableCountTasksToPerson(tasks, personEntity);
    }
}
