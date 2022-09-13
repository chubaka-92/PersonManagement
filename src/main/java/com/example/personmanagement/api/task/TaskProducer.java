package com.example.personmanagement.api.task;

import com.example.personmanagement.entity.TaskEntity;

public interface TaskProducer {
    void sendTask(TaskEntity taskEntity);
}
