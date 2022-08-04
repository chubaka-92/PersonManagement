package com.example.personmenegement.api;

import com.example.personmenegement.dto.TaskDto;

public interface TaskValidation {

    TaskDto validate(TaskDto taskDto);
}
