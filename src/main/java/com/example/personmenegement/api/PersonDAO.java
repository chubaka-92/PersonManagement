package com.example.personmenegement.api;

import com.example.personmenegement.entity.PersonEntity;

public interface PersonDAO {
    PersonEntity findPersonById(Long id);

    Long addPerson(PersonEntity personEntity);

    Long updatePerson(PersonEntity personEntity);

    void deletePersonById(long personId);
}
