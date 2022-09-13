package com.example.personmanagement.services.mapper;

import com.example.personmanagement.api.TaskMapper;
import com.example.personmanagement.dto.TaskDto;
import com.example.personmanagement.entity.TaskEntity;
import com.example.personmanagement.services.MessageService;
import com.example.personmanagement.types.Priority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class TaskMapperImp implements TaskMapper {

    private final MessageService messageService = new MessageService();

    public TaskDto taskEntityToTask(TaskEntity taskEntity) {
        log.info("Was calling taskEntityToTask. Input taskEntity: {}", taskEntity);
        return TaskDto.builder()
                .id(getId(taskEntity))
                .uid(taskEntity.getUid())
                .description(taskEntity.getDescription())
                .priority(messageService.getMessage(taskEntity.getPriority()))
                .build();
    }

    public TaskEntity taskToTaskEntity(TaskDto taskDto) {
        log.info("Was calling taskToTaskEntity. Input task: {}", taskDto);
        return TaskEntity.builder()
                .id(getId(taskDto))
                .uid(getUid(taskDto))
                .description(taskDto.getDescription().trim())
                .priority(Priority.definePriority(taskDto.getPriority()))
                .build();
    }

    public TaskEntity taskDtoAndTaskEntityToTaskEntity(TaskDto taskDto, TaskEntity taskEntity) {
        log.info("Was calling taskDtoAndTaskEntityToTaskEntity. Input taskDto: {}  taskEntity: {}", taskDto, taskEntity);
        return TaskEntity.builder()
                .id(taskEntity.getId())
                .uid(taskEntity.getUid())
                .description(taskDto.getDescription().trim())
                .priority(Priority.definePriority(taskDto.getPriority()))
                .personId(taskEntity.getPersonId())
                .build();
    }

    private String getId(TaskEntity taskEntity) {
        log.debug("Was calling getId. Input taskEntity: {}", taskEntity);
        if (taskEntity.getId() == null) { //todo использовать итерарный if
            return null;
        }
        return taskEntity.getId().toString();
    }

    private Long getId(TaskDto taskDto) {
        log.debug("Was calling getId. Input task: {}", taskDto);
        if (taskDto.getId() == null) { //todo использовать итерарный if
            return null;
        }
        return Long.valueOf(taskDto.getId());
    }

    private String getUid(TaskDto taskDto) {
        log.debug("Was calling getUid. Input task: {}", taskDto);
        if (taskDto.getUid() == null) { //todo использовать итерарный if
            return UUID.randomUUID().toString();
        }
        return taskDto.getUid();
    }

}
