package com.example.personmenegement.controller;

import com.example.personmenegement.api.PersonService;
import com.example.personmenegement.dto.PersonDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;


    @GetMapping("/{uid}")
    public ResponseEntity<PersonDto> getPerson(@PathVariable("uid") String uid) {
        log.info("Was calling getPerson. Input uid: {}", uid);
        return ResponseEntity.ok(personService.getPersonByUid(uid));
    }

    @GetMapping()
    public ResponseEntity<List<PersonDto>> getPersons() {
        log.info("Was calling getPersons.");
        return ResponseEntity.ok(personService.getPersons());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deletePerson(@PathVariable("id") Long id) {
        log.info("Was calling deletePerson. Input id: {}", id);
        return ResponseEntity.ok(personService.deletePerson(id));
    }

    @PostMapping("/add")
    public ResponseEntity<PersonDto> createPerson(@RequestBody PersonDto personDto) {
        log.info("Was calling createPerson. Input person: {}", personDto);
        return ResponseEntity.ok(personService.addNewPerson(personDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDto> updatePerson(@PathVariable("id") Long id, @RequestBody PersonDto personDto) {
        log.info("Was calling updatePerson. Input id: {} person: {}", id, personDto);
        return ResponseEntity.ok(personService.updatePerson(id, personDto));
    }

    @PostMapping()
    public ResponseEntity<List<PersonDto>> createPersons(@RequestBody List<PersonDto> personsDto) {
        log.info("Was calling createPersons.Input persons: {}", personsDto);
        return ResponseEntity.ok(personService.addNewPersons(personsDto));
    }
}