package com.example.personmanagement.api.dao;

import com.example.personmanagement.entity.TaskEntity;

import java.util.List;

public interface TaskDAO {

    List<TaskEntity> findTasks();

    TaskEntity addTask(TaskEntity taskEntity);

    TaskEntity updateTask(TaskEntity taskEntity);

    void deleteTaskById(Long id);

    TaskEntity findTaskByUid(String uid);

    TaskEntity findTaskById(Long taskId);
}
