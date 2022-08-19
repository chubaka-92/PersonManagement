package com.example.personmenegement.services.mapper;

import com.example.personmenegement.dto.TaskDto;
import com.example.personmenegement.entity.RoleEntity;
import com.example.personmenegement.entity.TaskEntity;
import com.example.personmenegement.types.Priority;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RoleMapperImp {

    public String roleToString(RoleEntity roleEntity) {
/*        log.info("Was calling roleToString. Input roleEntity: {}", roleEntity);
        return TaskDto.builder()
                .id(getId(taskEntity))// todo где-то используешь valueOf, где-то toString Лучше писать в одном стиле  //   Done
                .uid(taskEntity.getUid())
                .description(taskEntity.getDescription())
                .priority(messageService.getMessage(taskEntity.getPriority()))
                .build();*/
        return null;
    }

/*    public TaskEntity taskToTaskEntity(TaskDto taskDto) {
        log.info("Was calling taskToTaskEntity. Input task: {}", taskDto);
        return TaskEntity.builder()
                .id(getId(taskDto))
                .uid(getUid(taskDto))
                .description(taskDto.getDescription())
                .priority(Priority.definePriority(taskDto.getPriority()))
                .build();
    }*/
}
