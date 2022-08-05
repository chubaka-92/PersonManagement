package com.example.personmenegement.kafka;

import com.example.personmenegement.dto.TaskDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ListenerKafka {
    @KafkaListener(topics = "test_topic_0", groupId = "group_id")
    public void consumePerson(@Payload String TaskDto){
        //Thread.sleep(15000);
        System.out.println(TaskDto);
    }
}
