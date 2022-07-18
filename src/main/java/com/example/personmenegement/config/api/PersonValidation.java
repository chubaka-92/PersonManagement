package com.example.personmenegement.config.api;// todo вынеси пакет api из config

import com.example.personmenegement.soap.person.AddPersonResponse;
import com.example.personmenegement.soap.person.Person;
import com.example.personmenegement.soap.person.UpdatePersonResponse;

public interface PersonValidation {

    AddPersonResponse addPersonValidator(Person person);

    UpdatePersonResponse updatePersonValidator(Person person);
}
