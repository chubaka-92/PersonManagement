package com.example.personmanagement.api.person;

import com.example.personmanagement.entity.PersonEntity;

public interface PersonProducer {
    void sendTask(PersonEntity personEntity);
}
