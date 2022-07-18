package com.example.personmenegement.config.api;// todo вынеси пакет api из config

import com.example.personmenegement.entity.PersonEntity;
import com.example.personmenegement.soap.person.*;
import com.example.personmenegement.types.Status;

import java.text.MessageFormat;

public interface PersonService {

    GetPersonByIdResponse getPersonById(Long id);

    AddPersonResponse addNewPerson(Person person);

    UpdatePersonResponse updatePerson(Person person);

    DeletePersonResponse deletePerson(long id);
}
