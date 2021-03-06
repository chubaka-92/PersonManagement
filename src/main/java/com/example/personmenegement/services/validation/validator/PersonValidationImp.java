package com.example.personmenegement.services.validation.validator;

import com.example.personmenegement.api.PersonValidation;
import com.example.personmenegement.services.validation.cheker.PersonCheckerImp;
import com.example.personmenegement.services.validation.initializer.PersonInitializerImp;
import com.example.personmenegement.soap.person.Person;
import com.example.personmenegement.types.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.personmenegement.types.Position.UNDEFINED;
import static com.example.personmenegement.types.Position.getDefine;

@Service
@RequiredArgsConstructor
public class PersonValidationImp implements PersonValidation {

    public Person validate(Person person) {

        PersonCheckerImp personChecker = new PersonCheckerImp();
        PersonInitializerImp personErrorMessage = new PersonInitializerImp(person);

        personErrorMessage.addFieldsEmpty(personChecker.checkRequiredFields(person));

        Position position = getDefine(person.getPosition());

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
