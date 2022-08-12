package com.example.personmenegement.services;


import com.example.personmenegement.api.*;
import com.example.personmenegement.dto.PersonDto;
import com.example.personmenegement.entity.PersonEntity;
import com.example.personmenegement.exeption.PersonNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final MessageService messageService;
    private final PersonProducer personProducer;

    @Override
    public PersonDto getPersonByUid(String uid) {
        log.info("Was calling getPersonByUid. Input uid: {}", uid);
        PersonEntity personEntity = personDao.findPersonByUid(uid);

        if (personEntity == null) {
            log.error(MessageFormat.format(messageService.getMessage(PERSON_NOT_FOUND), uid));
            throw new PersonNotFoundException(MessageFormat.format(messageService.getMessage(PERSON_NOT_FOUND), uid));
        }
        return personMapper.personEntityToPerson(personEntity);
    }

    public List<PersonDto> getPersons() {
        log.info("Was calling getPersons.");
        List<PersonEntity> persons = personDao.findPersons();

        if (persons == null) {
            log.error(messageService.getMessage(PERSONS_NOT_FOUND));
            throw new PersonNotFoundException(messageService.getMessage(PERSONS_NOT_FOUND));
        }
        return persons.stream()
                .map(personMapper::personEntityToPerson)
                .collect(Collectors.toList());
    }

    public PersonDto addNewPerson(PersonDto personDto) {
        log.info("Was calling addNewPerson. Input person: {}", personDto);
        PersonDto personDtoResponse = personValidation.validate(personDto);

        if (personDtoResponse != null) {
            log.error(personDtoResponse.toString());
            return personDtoResponse;
        }
        PersonEntity personEntity = personMapper.personToPersonEntity(personDto);
        personProducer.sendTask(personEntity);
        return personMapper.personEntityToPerson(personEntity);
    }

    public PersonDto updatePerson(PersonDto personDto) {
        log.info("Was calling updatePerson. Input person: {}", personDto);
        PersonDto personDtoResponse = personValidation.validate(personDto);

        if (personDtoResponse != null) {
            log.error(personDtoResponse.toString());
            return personDtoResponse;
        }
        PersonEntity personEntity = personDao.updatePerson(personMapper.personToPersonEntity(personDto));
        personDtoResponse = personMapper.personEntityToPerson(personEntity);
        return personDtoResponse;
    }


    public Long deletePerson(Long id) {
        log.info("Was calling deletePerson. Input id: {}", id);
        if (personDao.findPersonById(id) == null) {
            log.error(MessageFormat.format(messageService.getMessage(PERSON_NOT_FOUND), id));
            throw new PersonNotFoundException(MessageFormat.format(messageService.getMessage(PERSON_NOT_FOUND), id));
        }
        personDao.deletePersonById(id);
        return id;
    }

    public List<PersonDto> addNewPersons(List<PersonDto> personsDto) {
        log.info("Was calling addNewPersons. Input persons: {}", personsDto);
        List<PersonDto> response = new ArrayList<>();
        for (PersonDto personDto : personsDto) {// todo используй стримы
            PersonDto personDtoResponse = personValidation.validate(personDto);
            if (personDtoResponse == null) {
                PersonEntity personEntity = personMapper.personToPersonEntity(personDto);
                personProducer.sendTask(personEntity);
                personDtoResponse = personMapper.personEntityToPerson(personEntity);
                response.add(personDtoResponse);
            } else {
                log.error(personDtoResponse.toString());
                response.add(personDtoResponse);
            }
        }
        return response;
    }
}
