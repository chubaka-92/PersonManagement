package com.example.personmanagement.api.task;

import com.example.personmanagement.dto.TaskDto;
import com.example.personmanagement.entity.TaskEntity;

public interface TaskMapper {

    TaskDto taskEntityToTask(TaskEntity taskEntity);

    TaskEntity taskToTaskEntity(TaskDto taskDto);

    TaskEntity taskDtoAndTaskEntityToTaskEntity(TaskDto taskDto, TaskEntity taskEntity);
}
