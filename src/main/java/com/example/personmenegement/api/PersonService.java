package com.example.personmenegement.api;

import com.example.personmenegement.dto.PersonDto;

import java.util.List;

public interface PersonService {

    List<PersonDto> getPersons();// todo сервис не должен возвращать ResponseEntity   //   DONE

    PersonDto addNewPerson(PersonDto personDto);// todo сервис не должен возвращать ResponseEntity   //   DONE

    List<PersonDto> addNewPersons(List<PersonDto> personsDto);// todo сервис не должен возвращать ResponseEntity   //   DONE

    PersonDto updatePerson(PersonDto personDto);// todo сервис не должен возвращать ResponseEntity   //   DONE

    Long deletePerson(Long id);// todo сервис не должен возвращать ResponseEntity   //   DONE

    PersonDto getPersonByUid(String uid);
}
