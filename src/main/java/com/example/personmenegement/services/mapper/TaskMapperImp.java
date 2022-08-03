package com.example.personmenegement.services.mapper;

import com.example.personmenegement.api.MessageService;
import com.example.personmenegement.api.TaskMapper;
import com.example.personmenegement.dto.Task;
import com.example.personmenegement.entity.TaskEntity;
import com.example.personmenegement.types.Priority;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskMapperImp implements TaskMapper {

    private final MessageService messageService;

    public Task taskEntityToTask(TaskEntity taskEntity) {
        log.info("Was calling taskEntityToTask. Input taskEntity: {}", taskEntity.toString());
        return Task.builder()
                .id(taskEntity.getId().toString())// todo где-то используешь valueOf, где-то toString Лучше писать в одном стиле
                .uid(taskEntity.getUid())
                .description(taskEntity.getDescription())
                .priority(messageService.getMessage(taskEntity.getPriority()))
                .build();
    }

    public TaskEntity taskToTaskEntity(Task task) {
        log.info("Was calling taskToTaskEntity. Input task: {}", task.toString());
        return TaskEntity.builder()
                .id(getId(task))
                .uid(getUid(task))
                .description(task.getDescription())
                .priority(Priority.definePriority(task.getPriority()))
                .build();
    }

    private Long getId(Task task) {
        log.debug("Was calling getId. Input task: {}", task.toString());
        if (task.getId() == null) {
            return null;
        }
        return Long.valueOf(task.getId());
    }

    private String getUid(Task task) {
        log.debug("Was calling getUid. Input task: {}", task.toString());
        if (task.getUid() == null) {
            return UUID.randomUUID().toString();
        }
        return task.getUid();
    }

}
