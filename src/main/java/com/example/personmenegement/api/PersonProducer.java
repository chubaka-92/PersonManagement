package com.example.personmenegement.api;

import com.example.personmenegement.entity.PersonEntity;

public interface PersonProducer {
    void sendTask(PersonEntity personEntity);
}
