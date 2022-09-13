package com.example.personmanagement.kafka;

import com.example.personmanagement.api.task.TaskProducer;
import com.example.personmanagement.entity.TaskEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskProducerImp implements TaskProducer {

    @Value("${topic.task.name}")
    private String TOPIC;
    private final KafkaTemplate<String, TaskEntity> kafkaTemplate;

    public void sendTask(TaskEntity taskEntity) {
        log.info("Was calling sendTask. Input taskEntity: {}", taskEntity);
        kafkaTemplate.send(TOPIC, taskEntity.getUid(), taskEntity);
    }
}
