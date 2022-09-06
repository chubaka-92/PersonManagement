package com.example.personmanagement.api;

import com.example.personmanagement.entity.PersonEntity;

public interface PersonProducer {
    void sendTask(PersonEntity personEntity);
}
