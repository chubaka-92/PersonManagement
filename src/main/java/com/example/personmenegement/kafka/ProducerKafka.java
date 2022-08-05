package com.example.personmenegement.kafka;

import com.example.personmenegement.dto.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerKafka {

    private  static final String TOPIC = "test_topic_0";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendTask(TaskDto taskDto){
        kafkaTemplate.send(TOPIC, taskDto.toString());
    }
}
