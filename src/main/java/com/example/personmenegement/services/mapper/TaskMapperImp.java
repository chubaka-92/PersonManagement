package com.example.personmenegement.services.mapper;

import com.example.personmenegement.api.MessageService;
import com.example.personmenegement.api.TaskMapper;
import com.example.personmenegement.dto.TaskDto;
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

    public TaskDto taskEntityToTask(TaskEntity taskEntity) {
        log.info("Was calling taskEntityToTask. Input taskEntity: {}", taskEntity);
        return TaskDto.builder()
                .id(taskEntity.getId().toString())
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
                .description(taskDto.getDescription())
                .priority(Priority.definePriority(taskDto.getPriority()))
                .build();
    }

    private Long getId(TaskDto taskDto) {
        log.debug("Was calling getId. Input task: {}", taskDto);
        if (taskDto.getId() == null) {
            return null;
        }
        return Long.valueOf(taskDto.getId());
    }

    private String getUid(TaskDto taskDto) {
        log.debug("Was calling getUid. Input task: {}", taskDto);
        if (taskDto.getUid() == null) {
            return UUID.randomUUID().toString();
        }
        return taskDto.getUid();
    }

}
