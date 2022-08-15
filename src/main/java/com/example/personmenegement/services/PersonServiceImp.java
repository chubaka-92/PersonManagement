package com.example.personmenegement.services;


import com.example.personmenegement.api.*;
import com.example.personmenegement.dto.PersonDto;
import com.example.personmenegement.entity.PersonEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonServiceImp implements PersonService {

    private final PersonDAO personDao;
    private final PersonMapper personMapper;
    private final PersonValidation personValidation;
    private final PersonProducer personProducer;
    ;

    @Override
    public PersonDto getPersonByUid(String uid) {
        log.info("Was calling getPersonByUid. Input uid: {}", uid);
        PersonEntity personEntity = personDao.findPersonByUid(uid);
        return personMapper.personEntityToPerson(personEntity);
    }

    public List<PersonDto> getPersons() {
        log.info("Was calling getPersons.");
        List<PersonEntity> persons = personDao.findPersons();
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
        return getNewPerson(personDto);
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
        personDao.deletePersonById(id);
        return id;
    }

    public List<PersonDto> addNewPersons(List<PersonDto> personsDto) {
        log.info("Was calling addNewPersons. Input persons: {}", personsDto);
        // todo используй стримы
        List<PersonDto> response = /*new ArrayList<>();
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
        List<PersonDto> response2 =*/ personsDto.stream()
                .map(this::getPersonDto)
                .collect(Collectors.toList());

        return response;
    }

    private PersonDto getPersonDto(PersonDto personDto) {
        PersonDto result = personValidation.validate(personDto);
        if (result == null) {
            return getNewPerson(personDto);
        }
        log.error(result.toString());
        return result;
    }

    private PersonDto getNewPerson(PersonDto personDto) {
        PersonEntity personEntity = personMapper.personToPersonEntity(personDto);
        personProducer.sendTask(personEntity);
        return personMapper.personEntityToPerson(personEntity);
    }
}
