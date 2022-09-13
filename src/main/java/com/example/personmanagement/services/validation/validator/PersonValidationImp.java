package com.example.personmanagement.services.validation.validator;

import com.example.personmanagement.api.PersonChecker;
import com.example.personmanagement.api.PersonInitializer;
import com.example.personmanagement.api.PersonValidation;
import com.example.personmanagement.dto.PersonDto;
import com.example.personmanagement.services.validation.cheker.PersonCheckerImp;
import com.example.personmanagement.services.validation.initializer.PersonInitializerImp;
import com.example.personmanagement.types.Position;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.personmanagement.types.Position.UNDEFINED;
import static com.example.personmanagement.types.Position.definePosition;

@Slf4j
@Service
public class PersonValidationImp implements PersonValidation {

    public PersonDto validate(PersonDto personDto) {
        log.info("Was calling validate. Input person: " + personDto);
        PersonChecker personChecker = new PersonCheckerImp();
        PersonInitializer personErrorMessage = new PersonInitializerImp(personDto);

        personErrorMessage.addFieldsEmpty(personChecker.checkRequiredFields(personDto));

        Position position = definePosition(personDto.getPosition());

        if (!personErrorMessage.hasErrors()) {
            checkingFilingFields(personDto, personErrorMessage, personChecker, position);
        }
        if (personErrorMessage.hasErrors()) {
            return personErrorMessage.getPersonDtoError();
        }
        return null;
    }

    private void checkingFilingFields(PersonDto personDto,
                                      PersonInitializer personErrorMessage,
                                      PersonChecker personChecker,
                                      Position position) {
        log.info("Was calling checkingFilingFields.");
        if (position != UNDEFINED) {
            personErrorMessage.addIncorrectArgumentMessage(personChecker.checkAge(personDto.getAge()));

            personErrorMessage.addIncorrectArgumentMessage(personChecker.checkSalary(position, personDto.getSalary()));

            personErrorMessage.addIncorrectArgumentMessage( //todo убрать перенос
                    personChecker.checkExperience(position, personDto.getExperience()));
        } else {
            personErrorMessage.addIncorrectArgumentMessage(personChecker.checkPosition(personDto.getPosition()));
        }
    }
}