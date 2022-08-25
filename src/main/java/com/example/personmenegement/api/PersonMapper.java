package com.example.personmenegement.api;

import com.example.personmenegement.dto.PersonDto;
import com.example.personmenegement.entity.PersonEntity;

public interface PersonMapper {

    PersonEntity personToPersonEntity(PersonDto personDto);

    PersonDto personEntityToPerson(PersonEntity personEntity);

    PersonEntity personDtoAndPersonEntityToPersonEntity(PersonDto personDto, PersonEntity personEntity);
}
