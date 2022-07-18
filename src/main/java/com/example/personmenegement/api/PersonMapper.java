package com.example.personmenegement.api;

import com.example.personmenegement.entity.PersonEntity;
import com.example.personmenegement.soap.person.Person;

public interface PersonMapper {

    PersonEntity personToPersonEntity(Person person);

    Person personEntityToPerson(PersonEntity personEntity);
}
