package com.example.personmenegement.services.validation.initializer;

import com.example.personmenegement.api.MessageService;
import com.example.personmenegement.api.PersonInitializer;
import com.example.personmenegement.dto.PersonDto;
import com.example.personmenegement.types.PersonFieldName;
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
    private final MessageService messageService;

    public void addFieldsEmpty(List<String> incorrectFields) {
        log.info("Was calling addFieldsEmpty. Input incorrectFields: " + incorrectFields);// todo toString  // DONE
        if (!incorrectFields.isEmpty()) {
            personDtoError.setValid(false);
        }
        for (String field : incorrectFields) {
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

    public void addIncorrectArgumentMessage(Map<String, String> incorrectArguments) {
        log.info("Was calling addIncorrectArgumentMessage. Input incorrectArguments: " + incorrectArguments);
        if (!incorrectArguments.isEmpty()) {
            personDtoError.setValid(false);
        }
        for (Map.Entry<String, String> entry : incorrectArguments.entrySet()) {
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
    }

    public boolean hasErrors() {
        log.info("Was calling hasErrors.");
        return !personDtoError.isValid();
    }
}
