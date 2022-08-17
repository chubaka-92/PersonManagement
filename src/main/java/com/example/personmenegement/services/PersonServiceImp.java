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

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonServiceImp implements PersonService {

    private final PersonDAO personDao;
    private final PersonMapper personMapper;
    private final PersonValidation personValidation;

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
        return personsDto.stream()
                .map(this::getPersonDto)
                .collect(Collectors.toList());
    }

    private PersonDto getPersonDto(PersonDto personDto) {
        log.debug("Was calling getPersonDto. Input personDto: {}", personDto);
        PersonDto result = personValidation.validate(personDto);
        if (result == null) {
            return getNewPerson(personDto);
        }
        log.error(result.toString());
        return result;
    }

    private PersonDto getNewPerson(PersonDto personDto) {
        log.debug("Was calling getNewPerson. Input personDto: {}", personDto);
        PersonEntity personEntity = personDao.addPerson(personMapper.personToPersonEntity(personDto));
        return personMapper.personEntityToPerson(personEntity);
    }
}
