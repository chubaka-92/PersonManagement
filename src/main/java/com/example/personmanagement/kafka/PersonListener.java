package com.example.personmanagement.kafka;

import com.example.personmanagement.api.PersonDAO;
import com.example.personmanagement.entity.PersonEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonListener {
    private final PersonDAO personDAO;

    @KafkaListener(topics = "${topic.person.name}", containerFactory = "personEntityKafkaListenerContainerFactory")
    public void savingPerson(@Payload PersonEntity personEntity) {
        log.info("Was calling savingPerson. Input personEntity: {}", personEntity);
        personDAO.addPerson(personEntity);
    }
}