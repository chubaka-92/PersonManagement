package com.example.personmanagement.api.initializer;

import com.example.personmanagement.dto.TaskDto;

import java.util.List;
import java.util.Map;

public interface TaskInitializer {

    void addFieldsEmpty(List<String> incorrectFields);

    void addIncorrectArgumentMessage(Map<String, String> incorrectArguments);

    boolean hasErrors();

    TaskDto getTaskDtoError();

    void setTaskDtoError(TaskDto taskDto);
}
