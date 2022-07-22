package com.example.personmenegement.api;
import com.example.personmenegement.soap.person.AddPersonResponse;
import com.example.personmenegement.soap.person.Person;
import com.example.personmenegement.soap.person.UpdatePersonResponse;

public interface PersonValidation {

    AddPersonResponse addPersonValidator(Person person);

    UpdatePersonResponse updatePersonValidator(Person person);
}
