package com.example.personmenegement.services;


import com.example.personmenegement.api.*;
import com.example.personmenegement.dao.PersonDAOImp;
import com.example.personmenegement.dto.Person;
import com.example.personmenegement.entity.PersonEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonServiceImp implements PersonService {
    private static final String PERSON_NOT_FOUND = "personNotFound";
    private static final String PERSONS_NOT_FOUND = "personsNotFound";
    private final PersonDAO personDao;
    private final PersonMapper personMapper;
    private final PersonValidation personValidation;
    private final ResourceBundleService resourceBundleService;

    public ResponseEntity getPersonById(Long id) {
        log.info("Was calling getPersonById. Input id: " + id);
        PersonEntity personEntity = personDao.findPersonById(id);

        if (personEntity == null) {
            log.error(MessageFormat.format(resourceBundleService.getString(PERSON_NOT_FOUND), id));
            return ResponseEntity.badRequest().body(MessageFormat.format(resourceBundleService.getString(PERSON_NOT_FOUND), id));
        }
        return ResponseEntity.ok(personMapper.personEntityToPerson(personEntity));

    }

    public ResponseEntity getPersons() {
        log.info("Was calling getPersons.");
        List<PersonEntity> persons = personDao.findPersons();

        if (persons == null) {
            log.error(resourceBundleService.getString(PERSONS_NOT_FOUND));
            return ResponseEntity.badRequest().body(resourceBundleService.getString(PERSONS_NOT_FOUND));
        }
        return ResponseEntity.ok(persons.stream()
                .map(personMapper::personEntityToPerson)
                .collect(Collectors.toList()));
    }

    public ResponseEntity addNewPerson(Person person) {
        log.info("Was calling addNewPerson. Input person: " + person);
        Person personResponse = personValidation.validate(person);

        if (personResponse!= null) {
            log.error(personResponse.toString());
            return ResponseEntity.badRequest().body(personResponse);
        }
        PersonEntity personEntity = personDao.addPerson(personMapper.personToPersonEntity(person));
        personResponse = personMapper.personEntityToPerson(personEntity);
        return ResponseEntity.ok(personResponse);
    }

    public ResponseEntity updatePerson(Person person) {
        log.info("Was calling updatePerson. Input person: " + person);
        Person personResponse = personValidation.validate(person);

        if (personResponse!= null) {
            log.error(personResponse.toString());
            return ResponseEntity.badRequest().body(personResponse);
        }
        PersonEntity personEntity = personDao.updatePerson(personMapper.personToPersonEntity(person));
        personResponse = personMapper.personEntityToPerson(personEntity);
        return ResponseEntity.ok(personResponse);
    }


    public ResponseEntity deletePerson(Long id) {
        log.info("Was calling deletePerson. Input id: " + id);
        if (personDao.findPersonById(id) == null) {
            log.error(MessageFormat.format(resourceBundleService.getString(PERSON_NOT_FOUND), id));
            return ResponseEntity.badRequest().body(MessageFormat.format(resourceBundleService.getString(PERSON_NOT_FOUND), id));
        }
        personDao.deletePersonById(id);
        return ResponseEntity.ok(id);
    }


    public ResponseEntity addNewPersons(List<Person> persons) {
        log.info("Was calling addNewPersons. Input persons: " + persons);
        List<Person> response = new ArrayList<>();
        for (Person person : persons) {
            Person personResponse = personValidation.validate(person);
            if (personResponse == null) {
                PersonEntity personEntity = personDao.addPerson(personMapper.personToPersonEntity(person));
                personResponse = personMapper.personEntityToPerson(personEntity);
                response.add(personResponse);
            } else {
                log.error(personResponse.toString());
                response.add(personResponse);
            }
        }
        return ResponseEntity.ok(response);
    }
}
