package com.example.personmenegement.dao;

import com.example.personmenegement.api.PersonDAO;
import com.example.personmenegement.entity.PersonEntity;
import com.example.personmenegement.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class PersonDAOImp implements PersonDAO {
    private final PersonRepository personRepository;

    public PersonEntity findPersonById(Long id) {
        log.info("Was calling findPersonById. Input id: {}", id);
        return personRepository.findById(id).orElse(null);
    }

    public List<PersonEntity> findPersons() {
        log.info("Was calling findPersons.");
        return personRepository.findAll();// todo лучше используй JpaRepository не нужны будут лишние касты   //   DONE
    }

    @Transactional
    public PersonEntity addPerson(PersonEntity personEntity) {
        log.info("Was calling addPerson. Input personEntity: {}", personEntity.toString());
        return personRepository.save(personEntity);
    }

    @Transactional
    public PersonEntity updatePerson(PersonEntity personEntity) {
        log.info("Was calling updatePerson. Input personEntity: {}", personEntity.toString());
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
        return personRepository.findByUid(uid).orElse(null);
    }
}
