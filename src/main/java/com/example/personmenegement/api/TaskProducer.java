package com.example.personmenegement.api;

import com.example.personmenegement.entity.TaskEntity;

public interface TaskProducer {
    void sendTask(TaskEntity taskEntity);
}
