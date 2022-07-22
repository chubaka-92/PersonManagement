package com.example.personmenegementrest.services.validation;

import com.example.personmenegementrest.api.PersonChecker;
import com.example.personmenegementrest.api.PersonValidation;
import com.example.personmenegementrest.dto.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonValidationImp implements PersonValidation {

    private final PersonChecker personChecker;

    public void addPersonValidator(Person person) {
        log.info("Was calling addPersonValidator. Input person: " + person.toString());
        personChecker.checkPersonRequiredFields(person);
        if (!person.isValid()) {
            return;
        }
        personChecker.checkPersonSalary(person);
        if (!person.isValid()) {
            return;
        }
        personChecker.checkPersonExperienceForPosition(person);

    }

    public void updatePersonValidator(Person person) {
        log.info("Was calling updatePersonValidator. Input person: " + person.toString());
        personChecker.checkPersonRequiredFields(person);
        if (!person.isValid()) {
            return;
        }
        personChecker.checkPersonSalary(person);
        if (!person.isValid()) {
            return;
        }
        personChecker.checkPersonExperienceForPosition(person);
    }

}
