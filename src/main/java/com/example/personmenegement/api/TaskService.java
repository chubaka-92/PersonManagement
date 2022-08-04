package com.example.personmenegement.api;

import com.example.personmenegement.dto.TaskDto;

import java.util.List;

public interface TaskService {

    TaskDto getTaskById(Long id);

    List<TaskDto> getTasks();

    Long deleteTask(Long id);

    TaskDto addNewTask(TaskDto taskDto, Long personId);

    TaskDto updateTask(TaskDto taskDto, Long personId);

    List<TaskDto> addNewTasks(List<TaskDto> tasksDto, Long personId);
}
