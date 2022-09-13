package com.example.personmanagement.api.person;

import com.example.personmanagement.entity.PersonEntity;

import java.util.List;

public interface PersonDAO {

    PersonEntity findPersonById(Long id);

    List<PersonEntity> findPersons();

    PersonEntity addPerson(PersonEntity personEntity);

    PersonEntity updatePerson(PersonEntity personEntity);

    void deletePersonById(Long personId);

    PersonEntity findPersonByUid(String uid);
}
