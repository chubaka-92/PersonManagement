package com.example.personmanagement.api;

import com.example.personmanagement.dto.TaskDto;

import java.util.List;
import java.util.Map;

public interface TaskChecker {

    List<String> checkRequiredFields(TaskDto taskDto);

    Map<String, String> checkPriority(String priority);
}
