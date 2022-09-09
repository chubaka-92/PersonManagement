package com.example.personmanagement.api;

import com.example.personmanagement.dto.TaskDto;

import java.util.List;

public interface TaskService {

    TaskDto getTaskByUid(String uid);

    List<TaskDto> getTasks();

    Long deleteTask(Long id);

    TaskDto addNewTask(TaskDto taskDto, Long personId);

    TaskDto updateTask(TaskDto taskDto, Long personId);

    List<TaskDto> addNewTasks(List<TaskDto> tasksDto, Long personId);
}
