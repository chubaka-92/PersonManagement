package com.example.personmenegement.services.validation.initializer;

import com.example.personmenegement.api.PersonInitializer;
import com.example.personmenegement.api.ResourceBundleService;
import com.example.personmenegement.dto.Person;
import com.example.personmenegement.services.ResourceBundleServiceImp;
import com.example.personmenegement.types.PersonFieldName;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
@Getter
public class PersonInitializerImp  implements PersonInitializer {
    private static final String EMPTY_FIELD = "emptyField";

    private final Person personError;
    private final ResourceBundleService messageService;

    public PersonInitializerImp(Person person) {
        this.personError = person;
        this.messageService = new ResourceBundleServiceImp();
    }

    public void addFieldsEmpty(List<String> incorrectFields) {
        log.info("Was calling addFieldsEmpty. Input incorrectFields: " + incorrectFields.toString());
        if (!incorrectFields.isEmpty()) {
            personError.setValid(false);
        }
        for (String field : incorrectFields) {
            switch (field) {
                case PersonFieldName.NAME: {
                    personError.setName(messageService.getString(EMPTY_FIELD));
                    break;
                }
                case PersonFieldName.AGE: {
                    personError.setAge(messageService.getString(EMPTY_FIELD));
                    break;
                }
                case PersonFieldName.POSITION: {
                    personError.setPosition(messageService.getString(EMPTY_FIELD));
                    break;
                }
                case PersonFieldName.SALARY: {
                    personError.setSalary(messageService.getString(EMPTY_FIELD));
                    break;
                }
                case PersonFieldName.EXPERIENCE: {
                    personError.setExperience(messageService.getString(EMPTY_FIELD));
                    break;
                }
                default:
                    break;
            }
        }
    }

    public void addIncorrectArgumentMessage(Map<String, String> incorrectArguments) {
        log.info("Was calling addIncorrectArgumentMessage. Input incorrectArguments: " + incorrectArguments.toString());
        if (!incorrectArguments.isEmpty()) {
            personError.setValid(false);
        }
        for (Map.Entry<String, String> entry : incorrectArguments.entrySet()) {
            switch (entry.getKey()) {
                case PersonFieldName.NAME: {
                    personError.setName(entry.getValue());
                    break;
                }
                case PersonFieldName.AGE: {
                    personError.setAge(entry.getValue());
                    break;
                }
                case PersonFieldName.POSITION: {
                    personError.setPosition(entry.getValue());
                    break;
                }
                case PersonFieldName.SALARY: {
                    personError.setSalary(entry.getValue());
                    break;
                }
                case PersonFieldName.EXPERIENCE: {
                    personError.setExperience(entry.getValue());
                    break;
                }
                default:
                    break;
            }
        }
    }

    public boolean hasErrors() {
        log.info("Was calling hasErrors.");
        return !personError.isValid();
    }
}
