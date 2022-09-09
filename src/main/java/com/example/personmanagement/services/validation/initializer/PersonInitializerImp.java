package com.example.personmanagement.services.validation.initializer;

import com.example.personmanagement.api.PersonInitializer;
import com.example.personmanagement.dto.PersonDto;
import com.example.personmanagement.services.MessageService;
import com.example.personmanagement.types.PersonFieldName;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
@Getter
@RequiredArgsConstructor
public class PersonInitializerImp implements PersonInitializer {
    private static final String EMPTY_FIELD = "emptyField";
    private final PersonDto personDtoError;
    private final MessageService messageService = new MessageService();

    public void addFieldsEmpty(List<String> incorrectFields) {
        log.info("Was calling addFieldsEmpty. Input incorrectFields: " + incorrectFields);
        if (!incorrectFields.isEmpty()) {
            personDtoError.setValid(false);
        }
        incorrectFields.forEach(this::settingEmptyFields);
    }

    public void addIncorrectArgumentMessage(Map<String, String> incorrectArguments) {
        log.info("Was calling addIncorrectArgumentMessage. Input incorrectArguments: " + incorrectArguments);
        if (!incorrectArguments.isEmpty()) {
            personDtoError.setValid(false);
        }
        incorrectArguments.entrySet().forEach(this::settingIncorrectArgumentMessage);
    }

    public boolean hasErrors() {
        log.info("Was calling hasErrors.");
        return !personDtoError.isValid();
    }

    private void settingIncorrectArgumentMessage(Map.Entry<String, String> entry) {
        log.debug("Was calling settingIncorrectArgumentMessage. Input entry: " + entry);
        switch (entry.getKey()) {
            case PersonFieldName.NAME: {
                personDtoError.setName(entry.getValue());
                break;
            }
            case PersonFieldName.AGE: {
                personDtoError.setAge(entry.getValue());
                break;
            }
            case PersonFieldName.POSITION: {
                personDtoError.setPosition(entry.getValue());
                break;
            }
            case PersonFieldName.SALARY: {
                personDtoError.setSalary(entry.getValue());
                break;
            }
            case PersonFieldName.EXPERIENCE: {
                personDtoError.setExperience(entry.getValue());
                break;
            }
            default:
                break;
        }
    }

    private void settingEmptyFields(String field) {
        log.debug("Was calling settingEmptyFields. Input field: " + field);
        switch (field) {
            case PersonFieldName.NAME: {
                personDtoError.setName(messageService.getMessage(EMPTY_FIELD));
                break;
            }
            case PersonFieldName.AGE: {
                personDtoError.setAge(messageService.getMessage(EMPTY_FIELD));
                break;
            }
            case PersonFieldName.POSITION: {
                personDtoError.setPosition(messageService.getMessage(EMPTY_FIELD));
                break;
            }
            case PersonFieldName.SALARY: {
                personDtoError.setSalary(messageService.getMessage(EMPTY_FIELD));
                break;
            }
            case PersonFieldName.EXPERIENCE: {
                personDtoError.setExperience(messageService.getMessage(EMPTY_FIELD));
                break;
            }
            default:
                break;
        }
    }
}