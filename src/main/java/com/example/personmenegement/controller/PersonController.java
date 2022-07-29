package com.example.personmenegement.controller;

import com.example.personmenegement.api.PersonService;
import com.example.personmenegement.dto.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    @GetMapping("/{id}")
    public ResponseEntity getPerson(@PathVariable("id") Long id) {
        log.info("Was calling getPerson. Input id: " + id);
        return personService.getPersonById(Long.valueOf(id));
    }

    @GetMapping()
    public ResponseEntity getPersons() {
        log.info("Was calling getPersons.");
        return personService.getPersons();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePerson(@PathVariable("id") Long id) {
        log.info("Was calling deletePerson. Input id: " + id);
        return personService.deletePerson(Long.valueOf(id));
    }

    @PostMapping("/person")
    public ResponseEntity createPerson(@RequestBody Person person) {
        log.info("Was calling createPerson. Input person: " + person.toString());
        return personService.addNewPerson(person);
    }

    @PutMapping()
    public ResponseEntity updatePerson(@RequestBody Person person) {
        log.info("Was calling updatePerson. Input person: " + person.toString());
        return personService.updatePerson(person);
    }

    @PostMapping()
    public ResponseEntity createPersons(@RequestBody List<Person> persons) {
        log.info("Was calling createPersons.Input persons: " + persons.toString());
        return personService.addNewPersons(persons);
    }
}
