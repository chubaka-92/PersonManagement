package com.example.personmenegement.api;

import com.example.personmenegement.dto.PersonDto;

import java.util.List;

public interface PersonService {

    List<PersonDto> getPersons();

    PersonDto addNewPerson(PersonDto personDto);

    List<PersonDto> addNewPersons(List<PersonDto> personsDto);

    PersonDto updatePerson(Long id, PersonDto personDto);

    Long deletePerson(Long id);

    PersonDto getPersonByUid(String uid);
}
