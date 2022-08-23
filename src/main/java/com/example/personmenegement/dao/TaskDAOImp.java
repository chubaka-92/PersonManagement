package com.example.personmenegement.dao;

import com.example.personmenegement.api.MessageService;
import com.example.personmenegement.api.TaskDAO;
import com.example.personmenegement.entity.TaskEntity;
import com.example.personmenegement.exeption.TaskNotFoundException;
import com.example.personmenegement.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TaskDAOImp implements TaskDAO {
    private final TaskRepository taskRepository;
    private static final String TASK_NOT_FOUND = "taskNotFound";
    private static final String TASKS_NOT_FOUND = "tasksNotFound";
    private final MessageService messageService;

    public List<TaskEntity> findTasks() {
        log.info("Was calling findTasks.");
        List<TaskEntity> tasks = taskRepository.findAll();
        if (tasks.size() == 0) {
            log.error(messageService.getMessage(TASKS_NOT_FOUND));
            throw new TaskNotFoundException(messageService.getMessage(TASKS_NOT_FOUND));
        }
        return tasks;
    }

    @Transactional
    public TaskEntity addTask(TaskEntity taskEntity) {
        log.info("Was calling addTask. Input taskEntity: {}", taskEntity);
        return taskRepository.save(taskEntity);
    }

    @Transactional
    public TaskEntity updateTask(TaskEntity taskEntity) {
        log.info("Was calling updateTask. Input taskEntity: {}", taskEntity);
        return taskRepository.save(taskEntity);
    }

    @Transactional
    public void deleteTaskById(Long id) {
        log.info("Was calling deleteTaskById. Input id: {}", id);
        try {
            taskRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.error(messageService.getMessage(TASKS_NOT_FOUND));
            throw new TaskNotFoundException(messageService.getMessage(TASKS_NOT_FOUND));
        }
    }

    @Override
    public TaskEntity findTaskByUid(String uid) {
        log.info("Was calling findTaskByUid. Input uid: {}", uid);
        return taskRepository.findByUid(uid).orElseThrow(() -> {
            log.error(MessageFormat.format(messageService.getMessage(TASK_NOT_FOUND), uid));
            throw new TaskNotFoundException(MessageFormat.format(messageService.getMessage(TASK_NOT_FOUND), uid));
        });
    }

    @Override
    public TaskEntity findTaskById(Long id) {
        log.info("Was calling findTaskById. Input uid: {}", id);
        return taskRepository.findById(id).orElseThrow(() -> {
            log.error(MessageFormat.format(messageService.getMessage(TASK_NOT_FOUND), id));
            throw new TaskNotFoundException(MessageFormat.format(messageService.getMessage(TASK_NOT_FOUND), id));
        });
    }
}
