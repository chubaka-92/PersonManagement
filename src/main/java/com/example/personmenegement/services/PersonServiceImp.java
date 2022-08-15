package com.example.personmenegement.services;


import com.example.personmenegement.api.PersonDAO;
import com.example.personmenegement.api.PersonMapper;
import com.example.personmenegement.api.PersonService;
import com.example.personmenegement.api.PersonValidation;
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

    public PersonDto getPersonById(Long id) {
        log.info("Was calling getPersonById. Input id: {}", id);
        PersonEntity personEntity = personDao.findPersonById(id);
        // todo если бросаешь Exception, то лучше бросай его сразу в PersonDao
        //  Done
        return personMapper.personEntityToPerson(personEntity);

    }

    public List<PersonDto> getPersons() {
        log.info("Was calling getPersons.");
        List<PersonEntity> persons = personDao.findPersons();
        // todo если бросаешь Exception, то лучше бросай его сразу в PersonDao
        //  Done
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
        PersonEntity personEntity = personDao.addPerson(personMapper.personToPersonEntity(personDto));
        personDtoResponse = personMapper.personEntityToPerson(personEntity);
        return personDtoResponse;
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
        // todo если бросаешь Exception, то лучше бросай его сразу в PersonDao
        //  Done
        personDao.deletePersonById(id);
        return id;
    }


    public List<PersonDto> addNewPersons(List<PersonDto> personsDto) {
        log.info("Was calling addNewPersons. Input persons: {}", personsDto);
        List<PersonDto> response = new ArrayList<>();
        for (PersonDto personDto : personsDto) {
            PersonDto personDtoResponse = personValidation.validate(personDto);
            if (personDtoResponse == null) {
                PersonEntity personEntity = personDao.addPerson(personMapper.personToPersonEntity(personDto));
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
