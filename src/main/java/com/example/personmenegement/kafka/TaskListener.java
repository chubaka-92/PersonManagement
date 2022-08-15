package com.example.personmenegement.kafka;

import com.example.personmenegement.dao.TaskDAOImp;
import com.example.personmenegement.entity.TaskEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskListener {
    private final TaskDAOImp taskDAO;

    @KafkaListener(topics = "${topic.task.name}", containerFactory = "taskEntityKafkaListenerContainerFactory")
    public void savingTask(@Payload TaskEntity taskEntity) {
        log.info("Was calling savingTask. Input taskEntity: {}", taskEntity);
        taskDAO.addTask(taskEntity);
    }
}
