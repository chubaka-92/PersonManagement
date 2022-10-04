package com.example.personmanagement.api.producer;

import com.example.personmanagement.entity.TaskEntity;

public interface TaskProducer {
    void sendTask(TaskEntity taskEntity);
}
