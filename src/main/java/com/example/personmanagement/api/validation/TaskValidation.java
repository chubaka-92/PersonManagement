package com.example.personmanagement.api.validation;

import com.example.personmanagement.dto.TaskDto;

public interface TaskValidation {

    TaskDto validate(TaskDto taskDto);
}
