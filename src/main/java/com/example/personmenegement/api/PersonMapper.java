package com.example.personmenegement.api;

import com.example.personmenegement.dto.Person;
import com.example.personmenegement.entity.PersonEntity;

public interface PersonMapper {

    PersonEntity personToPersonEntity(Person person);

    Person personEntityToPerson(PersonEntity personEntity);
}
