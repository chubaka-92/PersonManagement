package com.example.personmanagement.api.service;

import com.example.personmanagement.dto.PersonDto;

import java.util.List;

public interface PersonService {

    List<PersonDto> getPersons();

    PersonDto addNewPerson(PersonDto personDto);

    List<PersonDto> addNewPersons(List<PersonDto> personsDto);

    PersonDto updatePerson(Long id, PersonDto personDto);

    Long deletePerson(Long id);

    PersonDto getPersonByUid(String uid);
}
