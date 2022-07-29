package com.example.personmenegement.api;

import com.example.personmenegement.dto.Task;
import com.example.personmenegement.entity.TaskEntity;

public interface TaskMapper {

    Task taskEntityToTask(TaskEntity taskEntity);

    TaskEntity taskToTaskEntity(Task task);
}
