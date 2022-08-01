package com.example.personmenegement.services.validation.validator;

import com.example.personmenegement.api.PersonValidation;
import com.example.personmenegement.dto.Person;
import com.example.personmenegement.services.validation.cheker.PersonCheckerImp;
import com.example.personmenegement.services.validation.initializer.PersonInitializerImp;
import com.example.personmenegement.types.Position;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.personmenegement.types.Position.UNDEFINED;
import static com.example.personmenegement.types.Position.definePosition;

@Slf4j
@Service
public class PersonValidationImp implements PersonValidation {

    public Person validate(Person person) {
        log.info("Was calling validate. Input person: " + person.toString());
        PersonCheckerImp personChecker = new PersonCheckerImp();
        PersonInitializerImp personErrorMessage = new PersonInitializerImp(person);

        personErrorMessage.addFieldsEmpty(personChecker.checkRequiredFields(person));

        Position position = definePosition(person.getPosition());

        if (!personErrorMessage.hasErrors()) {
            if (position != UNDEFINED) {
                personErrorMessage.addIncorrectArgumentMessage(
                        personChecker.checkAge(person.getAge()));

                personErrorMessage.addIncorrectArgumentMessage(
                        personChecker.checkSalary(position, person.getSalary()));

                personErrorMessage.addIncorrectArgumentMessage(
                        personChecker.checkExperience(position, person.getExperience()));
            } else {
                personErrorMessage.addIncorrectArgumentMessage(personChecker.checkPosition(person.getPosition()));
            }
        }
        if (personErrorMessage.hasErrors()) {
            return personErrorMessage.getPersonError();
        }
        return null;
    }
}
