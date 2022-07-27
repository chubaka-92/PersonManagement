package com.example.personmenegement.api;

import com.example.personmenegement.entity.PersonEntity;

public interface PersonDAO {
    PersonEntity findPersonById(Long id);

    PersonEntity addPerson(PersonEntity personEntity);

    PersonEntity updatePerson(PersonEntity personEntity);

    void deletePersonById(long personId);
}
