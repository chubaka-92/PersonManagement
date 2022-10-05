package com.example.personmanagement.services.mapper;

import com.example.personmanagement.api.mapper.TaskMapper;
import com.example.personmanagement.dto.TaskDto;
import com.example.personmanagement.entity.TaskEntity;
import com.example.personmanagement.services.MessageService;
import com.example.personmanagement.types.Priorities;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskMapperImp implements TaskMapper {

    private final MessageService messageService;
    private final Priorities priorities;

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
                .priority(priorities.getPriority(taskDto.getPriority()))
                .build();
    }

    public TaskEntity taskDtoAndTaskEntityToTaskEntity(TaskDto taskDto, TaskEntity taskEntity) {
        log.info("Was calling taskDtoAndTaskEntityToTaskEntity. Input taskDto: {}  taskEntity: {}", taskDto, taskEntity);
        return TaskEntity.builder()
                .id(taskEntity.getId())
                .uid(taskEntity.getUid())
                .description(taskDto.getDescription().trim())
                .priority(priorities.getPriority(taskDto.getPriority()))
                .personId(taskEntity.getPersonId())
                .build();
    }

    private String getId(TaskEntity taskEntity) {
        log.debug("Was calling getId. Input taskEntity: {}", taskEntity);
        return taskEntity.getId() == null ? null : taskEntity.getId().toString();
    }

    private Long getId(TaskDto taskDto) {
        log.debug("Was calling getId. Input task: {}", taskDto);
        return taskDto.getId() == null ? null : Long.valueOf(taskDto.getId());
    }

    private String getUid(TaskDto taskDto) {
        log.debug("Was calling getUid. Input task: {}", taskDto);
        return taskDto.getUid() == null ? UUID.randomUUID().toString() : taskDto.getUid();
    }

}
