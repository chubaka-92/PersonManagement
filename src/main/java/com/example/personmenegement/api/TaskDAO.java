package com.example.personmenegement.api;

import com.example.personmenegement.entity.TaskEntity;

import java.util.List;

public interface TaskDAO {
    TaskEntity findTaskById(Long id);

    List<TaskEntity> findTasks();

    TaskEntity addTask(TaskEntity taskEntity);

    TaskEntity updateTask(TaskEntity taskEntity);

    void deleteTaskById(long id);
}
