package com.example.personmenegement.api;

import com.example.personmenegement.dto.PersonDto;

import java.util.List;

public interface PersonService {

    PersonDto getPersonById(Long id);

    List<PersonDto> getPersons();

    PersonDto addNewPerson(PersonDto personDto);

    List<PersonDto> addNewPersons(List<PersonDto> personsDto);

    PersonDto updatePerson(PersonDto personDto);

    Long deletePerson(Long id);
}
