package com.example.personmenegement.api;

import com.example.personmenegement.soap.person.*;

public interface PersonService {

    GetPersonByIdResponse getPersonById(Long id);

    AddPersonResponse addNewPerson(Person person);

    UpdatePersonResponse updatePerson(Person person);

    DeletePersonResponse deletePerson(long id);
}
