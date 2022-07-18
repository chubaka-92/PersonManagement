package com.example.personmenegement.config.api; // todo вынеси пакет api из config

import com.example.personmenegement.soap.person.Person;
import com.example.personmenegement.soap.person.ServiceStatus;

public interface PersonChecker {

    void checkPersonRequiredFields(Person person);

    ServiceStatus checkPersonSalary(Person person);

    ServiceStatus checkPersonExperienceForPosition(Person person);
}
