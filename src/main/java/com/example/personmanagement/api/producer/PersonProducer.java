package com.example.personmanagement.api.producer;

import com.example.personmanagement.entity.PersonEntity;

public interface PersonProducer {
    void sendTask(PersonEntity personEntity);
}
