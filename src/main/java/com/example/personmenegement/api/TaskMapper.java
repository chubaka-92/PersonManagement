package com.example.personmenegement.api;

import com.example.personmenegement.dto.TaskDto;
import com.example.personmenegement.entity.TaskEntity;

public interface TaskMapper {

    TaskDto taskEntityToTask(TaskEntity taskEntity);

    TaskEntity taskToTaskEntity(TaskDto taskDto);
}
