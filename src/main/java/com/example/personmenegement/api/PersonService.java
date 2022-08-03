package com.example.personmenegement.api;

import com.example.personmenegement.dto.Person;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PersonService {

    ResponseEntity getPersonById(Long id);// todo сервис не должен возвращать ResponseEntity, лучше обертывать возвращаемое значение в контроллере

    ResponseEntity getPersons();// todo сервис не должен возвращать ResponseEntity

    ResponseEntity addNewPerson(Person person);// todo сервис не должен возвращать ResponseEntity

    ResponseEntity addNewPersons(List<Person> person);// todo сервис не должен возвращать ResponseEntity

    ResponseEntity updatePerson(Person person);// todo сервис не должен возвращать ResponseEntity

    ResponseEntity deletePerson(Long id);// todo сервис не должен возвращать ResponseEntity
}
