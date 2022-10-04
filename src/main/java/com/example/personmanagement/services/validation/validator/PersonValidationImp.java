package com.example.personmanagement.services.validation.validator;

import com.example.personmanagement.api.checker.PersonChecker;
import com.example.personmanagement.api.initializer.PersonInitializer;
import com.example.personmanagement.api.validation.PersonValidation;
import com.example.personmanagement.dto.PersonDto;
import com.example.personmanagement.types.Position;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.personmanagement.types.Position.UNDEFINED;
import static com.example.personmanagement.types.Position.definePosition;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonValidationImp implements PersonValidation {

    private final PersonChecker personChecker;
    private final PersonInitializer personErrorMessage;

    public PersonDto validate(PersonDto personDto) {
        log.info("Was calling validate. Input person: {}", personDto);
        personErrorMessage.setPersonDtoError(personDto);
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

            personErrorMessage.addIncorrectArgumentMessage(personChecker.checkExperience(position, personDto.getExperience()));
        } else {
            personErrorMessage.addIncorrectArgumentMessage(personChecker.checkPosition(personDto.getPosition()));
        }
    }
}