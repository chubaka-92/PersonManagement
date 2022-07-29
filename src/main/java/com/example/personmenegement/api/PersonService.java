package com.example.personmenegement.api;

import com.example.personmenegement.dto.Person;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PersonService {

    ResponseEntity getPersonById(Long id);

    ResponseEntity getPersons();

    ResponseEntity addNewPerson(Person person);

    ResponseEntity addNewPersons(List<Person> person);

    ResponseEntity updatePerson(Person person);

    ResponseEntity deletePerson(Long id);
}
