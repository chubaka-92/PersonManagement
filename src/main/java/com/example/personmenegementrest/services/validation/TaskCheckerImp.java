package com.example.personmenegementrest.services.validation;

import com.example.personmenegementrest.api.TaskChecker;
import com.example.personmenegementrest.dto.Task;
import com.example.personmenegementrest.entity.PersonEntity;
import com.example.personmenegementrest.types.Priority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ResourceBundle;

@Slf4j
@Service
public class TaskCheckerImp implements TaskChecker {

    private static final String EMPTY_FIELD = "emptyField";
    private static final String INCORRECT_PRIORITY = "incorrectPriority";
    private final ResourceBundle errorMsg = ResourceBundle.getBundle("message");

    @Override
    public void checkTaskRequiredFields(Task task) {
        log.info("Was calling checkTaskRequiredFields. Input task: " + task.toString());
        if (task.getDescription() == null || task.getDescription().trim().equals("")) {
            task.setDescription(errorMsg.getString(EMPTY_FIELD));
            task.setValid(false);
        }
        if (task.getPriority() == null || task.getPriority().trim().equals("")) {
            task.setDescription(errorMsg.getString(EMPTY_FIELD));
            task.setValid(false);
        }
        if (task.getPriority() != null && Priority.getDefine(task.getPriority()) == Priority.UNDEFINED) {
            task.setPriority(errorMsg.getString(INCORRECT_PRIORITY));
            task.setValid(false);
        }
    }

    @Override
    public boolean checkAvailableCountTasksToPerson(List<Task> tasks, PersonEntity personEntity) {
        log.info("Was calling checkAvailableCountTasksToPerson. Input personEntity: " + personEntity.toString()
                + " tasks: " + tasks.toString());
        if (personEntity.getTasks().size() < personEntity.getPosition().getCountTasks()
                && personEntity.getCountAvailableTasks() >= tasks.size()) {
            return false;
        }
        return true;
    }
}
