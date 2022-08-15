package com.example.personmenegement.dao;

import com.example.personmenegement.api.MessageService;
import com.example.personmenegement.api.PersonDAO;
import com.example.personmenegement.entity.PersonEntity;
import com.example.personmenegement.exeption.PersonNotFoundException;
import com.example.personmenegement.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PersonDAOImp implements PersonDAO {
    private static final String PERSON_NOT_FOUND = "personNotFound";
    private static final String PERSONS_NOT_FOUND = "personsNotFound";
    private final PersonRepository personRepository;
    private final MessageService messageService;

    public PersonEntity findPersonById(Long id) {
        log.info("Was calling findPersonById. Input id: {}", id);
        PersonEntity personEntity = personRepository.findById(id).orElse(null);
        if (personEntity == null) {
            log.error(MessageFormat.format(messageService.getMessage(PERSON_NOT_FOUND), id));
            throw new PersonNotFoundException(MessageFormat.format(messageService.getMessage(PERSON_NOT_FOUND), id));
        }
        return personEntity;
    }

    public List<PersonEntity> findPersons() {
        log.info("Was calling findPersons.");
        List<PersonEntity> persons = personRepository.findAll();
        if (persons.size() == 0) {
            log.error(messageService.getMessage(PERSONS_NOT_FOUND));
            throw new PersonNotFoundException(messageService.getMessage(PERSONS_NOT_FOUND));
        }
        return persons;
    }

    @Transactional
    public PersonEntity addPerson(PersonEntity personEntity) {
        log.info("Was calling addPerson. Input personEntity: {}", personEntity);
        return personRepository.save(personEntity);
    }

    @Transactional
    public PersonEntity updatePerson(PersonEntity personEntity) {
        log.info("Was calling updatePerson. Input personEntity: {}", personEntity);
        return personRepository.save(personEntity);
    }

    @Transactional
    public void deletePersonById(long personId) {
        log.info("Was calling deletePersonById. Input personId: {}", personId);
        personRepository.deleteById(personId);
    }

    @Override
    public PersonEntity findPersonByUid(String uid) {
        log.info("Was calling findPersonByUid. Input id: {}", uid);
        PersonEntity personEntity = personRepository.findByUid(uid).orElse(null);
        if (personEntity == null) {
            log.error(MessageFormat.format(messageService.getMessage(PERSON_NOT_FOUND), uid));
            throw new PersonNotFoundException(MessageFormat.format(messageService.getMessage(PERSON_NOT_FOUND), uid));
        }
        return personEntity;
    }
}
