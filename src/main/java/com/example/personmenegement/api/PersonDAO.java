package com.example.personmenegement.api;

import com.example.personmenegement.entity.PersonEntity;

import java.util.List;

public interface PersonDAO {

    PersonEntity findPersonById(Long id);

    List<PersonEntity> findPersons();

    PersonEntity addPerson(PersonEntity personEntity);

    PersonEntity updatePerson(PersonEntity personEntity);

    void deletePersonById(long personId);

    PersonEntity findPersonByUid(String uid);
}
