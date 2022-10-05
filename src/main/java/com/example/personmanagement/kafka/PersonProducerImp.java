package com.example.personmanagement.kafka;

import com.example.personmanagement.api.producer.PersonProducer;
import com.example.personmanagement.entity.PersonEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonProducerImp implements PersonProducer {
    @Value("${topic.person.name}")
    private String topic;
    private final KafkaTemplate<String, PersonEntity> kafkaTemplate;

    public void sendTask(PersonEntity personEntity) {
        log.info("Was calling sendTask. Input personEntity: {}", personEntity);
        kafkaTemplate.send(topic, personEntity.getName(), personEntity);
    }
}