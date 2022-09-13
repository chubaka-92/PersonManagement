package com.example.personmanagement.api.person;

import com.example.personmanagement.dto.PersonDto;
import com.example.personmanagement.entity.PersonEntity;

public interface PersonMapper {

    PersonEntity personToPersonEntity(PersonDto personDto);

    PersonDto personEntityToPerson(PersonEntity personEntity);

    PersonEntity personDtoAndPersonEntityToPersonEntity(PersonDto personDto, PersonEntity personEntity);
}
