package com.example.personmenegement.services;

import com.example.personmenegement.api.*;
import com.example.personmenegement.dao.TaskDAOImp;
import com.example.personmenegement.dto.Task;
import com.example.personmenegement.entity.PersonEntity;
import com.example.personmenegement.entity.TaskEntity;
import com.example.personmenegement.types.Position;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImp implements TaskService {

    private static final int ONE_TASK = 1;
    private final TaskDAOImp taskDAO;
    private static final String TOO_MANY_TASKS = "tooManyTasks";
    private static final String TASK_NOT_FOUND = "taskNotFound";
    private static final String TASKS_NOT_FOUND = "tasksNotFound";
    private static final String PERSON_NOT_FOUND = "personNotFound";
    private final MessageService messageService;
    private final TaskMapper taskMapper;
    private final TaskValidation taskValidation;
    private final PersonDAO personDao;

    public ResponseEntity getTaskById(Long id) {
        log.info("Was calling getTaskById. Input id: {}", id);
        TaskEntity taskEntity = taskDAO.findTaskById(id);

        if (taskEntity == null) {
            log.error(MessageFormat.format(messageService.getMessage(TASK_NOT_FOUND), id));
            return ResponseEntity.badRequest().body(MessageFormat.format(messageService.getMessage(TASK_NOT_FOUND), id));
        }
        return ResponseEntity.ok(taskMapper.taskEntityToTask(taskEntity));
    }

    public ResponseEntity getTasks() {
        log.info("Was calling getTasks.");
        List<TaskEntity> persons = taskDAO.findTasks();

        if (persons == null) {
            log.error(messageService.getMessage(TASKS_NOT_FOUND));
            return ResponseEntity.badRequest().body(messageService.getMessage(TASKS_NOT_FOUND));
        }
        return ResponseEntity.ok(persons.stream()
                .map(taskMapper::taskEntityToTask)
                .collect(Collectors.toList()));
    }

    public ResponseEntity deleteTask(Long id) {
        log.info("Was calling deleteTask. Input id: {}", id);
        if (taskDAO.findTaskById(id) == null) {
            log.error(MessageFormat.format(messageService.getMessage(TASK_NOT_FOUND), id));
            return ResponseEntity.badRequest().body(MessageFormat.format(messageService.getMessage(TASK_NOT_FOUND), id));
        }
        taskDAO.deleteTaskById(id);
        return ResponseEntity.ok(id);
    }

    public ResponseEntity addNewTask(Task task, Long personId) {
        log.info("Was calling addNewTask. Input task: {} personId: {}", task, personId);
        PersonEntity personEntity = personDao.findPersonById(personId);
        if (personEntity == null) {
            log.error(MessageFormat.format(messageService.getMessage(PERSON_NOT_FOUND), personId));
            return ResponseEntity.badRequest().body(MessageFormat.format(messageService.getMessage(PERSON_NOT_FOUND), personId));
        }
        if (Position.checkAvailableCountTasksToPerson(ONE_TASK, personEntity)) {
            log.error(MessageFormat.format(messageService.getMessage(TOO_MANY_TASKS), personEntity.getCountAvailableTasks()));
            return ResponseEntity.badRequest().body(MessageFormat.format(messageService.getMessage(TOO_MANY_TASKS), personEntity.getCountAvailableTasks()));
        }
        Task taskTemp = taskValidation.validate(task);
        if (taskTemp != null) {
            log.error(taskTemp.toString());
            return ResponseEntity.badRequest().body(taskTemp);
        }
        return ResponseEntity.ok(getNewTask(personEntity, task));
    }

    @Override
    public ResponseEntity addNewTasks(List<Task> tasks, Long personId) {
        log.info("Was calling addNewTasks. Input tasks: {} personId: {}", tasks, personId);
        PersonEntity personEntity = personDao.findPersonById(personId);
        if (personEntity == null) {
            log.error(MessageFormat.format(messageService.getMessage(PERSON_NOT_FOUND), personId));
            return ResponseEntity.badRequest().body(MessageFormat.format(messageService.getMessage(PERSON_NOT_FOUND), personId));
        }
        if (Position.checkAvailableCountTasksToPerson(tasks.size(), personEntity)) {
            log.error(MessageFormat.format(messageService.getMessage(TOO_MANY_TASKS), personEntity.getCountAvailableTasks()));
            return ResponseEntity.badRequest().body(MessageFormat.format(messageService.getMessage(TOO_MANY_TASKS), personEntity.getCountAvailableTasks()));
        }
        List<Task> response = new ArrayList<>();
        for (Task task : tasks) {
            Task taskTemp = taskValidation.validate(task);
            if (taskTemp == null) {
                response.add(getNewTask(personEntity, task));
            } else {
                log.error(task.toString());
                response.add(task);
            }
        }
        return ResponseEntity.ok(response);
    }

    public ResponseEntity updateTask(Task task) {
        log.info("Was calling updateTask. Input task: {}", task);
        Task taskTemp = taskValidation.validate(task);
        if (!taskTemp.isValid()) {
            log.error(taskTemp.toString());
            return ResponseEntity.badRequest().body(taskTemp);
        }
        return ResponseEntity.ok(getUpdateTask(taskTemp));
    }

    private Task getNewTask(PersonEntity personEntity, Task taskTemp) {
        log.debug("Was calling addNewTasks. Input personEntity: {} taskTemp: {}", personEntity, taskTemp);
        TaskEntity taskEntity = taskMapper.taskToTaskEntity(taskTemp);
        taskEntity.setPerson(personEntity);
        taskEntity.setId(taskDAO.addTask(taskEntity).getId());
        return taskMapper.taskEntityToTask(taskEntity);
    }

    private Object getUpdateTask(Task taskTemp) {
        log.debug("Was calling getUpdateTask. Input taskTemp: {}", taskTemp);
        TaskEntity taskEntity = taskMapper.taskToTaskEntity(taskTemp);
        taskEntity.setId(taskDAO.updateTask(taskEntity).getId());
        return taskMapper.taskEntityToTask(taskEntity);
    }
}
