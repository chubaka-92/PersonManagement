package com.example.personmenegement.api; // todo вынеси пакет api из config
                                          //   done

import com.example.personmenegement.soap.person.Person;
import com.example.personmenegement.soap.person.ServiceStatus;

public interface PersonChecker {

    void checkPersonRequiredFields(Person person);

    ServiceStatus checkPersonSalary(Person person);

    ServiceStatus checkPersonExperienceForPosition(Person person);
}
