package com.example.personmanagement.services.validation.initializer;

import com.example.personmanagement.api.user.UserInitializer;
import com.example.personmanagement.dto.UserDto;
import com.example.personmanagement.services.MessageService;
import com.example.personmanagement.types.UserFieldName;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@Getter
@RequiredArgsConstructor
public class UserInitializerImp implements UserInitializer {
    private static final String EMPTY_FIELD = "emptyField";
    private final UserDto userDtoError;
    private final MessageService messageService = new MessageService();

    public void addFieldsEmpty(List<String> incorrectFields) {
        log.info("Was calling addFieldsEmpty. Input incorrectFields: {}", incorrectFields);
        if (!incorrectFields.isEmpty()) {
            userDtoError.setValid(false);
        }
        incorrectFields.forEach(this::settingEmptyFields);
    }

    public void addIncorrectArgumentMessage(Map<String, String> incorrectArguments) {
        log.info("Was calling addIncorrectArgumentMessage. Input incorrectArguments: {}", incorrectArguments);
        if (!incorrectArguments.isEmpty()) {
            userDtoError.setValid(false);
        }
        incorrectArguments.entrySet().forEach(this::settingIncorrectArgumentMessage);
    }

    public boolean hasErrors() {
        log.info("Was calling hasErrors.");
        return !userDtoError.isValid();
    }

    private void settingIncorrectArgumentMessage(Map.Entry<String, String> entry) {
        log.debug("Was calling settingIncorrectArgumentMessage. Input entry: {}", entry);
        switch (entry.getKey()) {
            case UserFieldName.USER_NAME: {
                userDtoError.setUsername(entry.getValue());
                break;
            }
            case UserFieldName.EMAIL: {
                userDtoError.setEmail(entry.getValue());
                break;
            }
            case UserFieldName.PASSWORD: {
                userDtoError.setPassword(entry.getValue());
                break;
            }
            case UserFieldName.ROLES: {
                userDtoError.setRoles(Collections.singleton(entry.getValue()));
                break;
            }
            default:
                break;
        }
    }

    private void settingEmptyFields(String field) {
        log.debug("Was calling settingEmptyFields. Input field: {}", field);
        switch (field) {
            case UserFieldName.USER_NAME: {
                userDtoError.setUsername(messageService.getMessage(EMPTY_FIELD));
                break;
            }
            case UserFieldName.EMAIL: {
                userDtoError.setEmail(messageService.getMessage(EMPTY_FIELD));
                break;
            }
            case UserFieldName.PASSWORD: {
                userDtoError.setPassword(messageService.getMessage(EMPTY_FIELD));
                break;
            }
            case UserFieldName.ROLES: {
                userDtoError.setRoles(Collections.singleton(messageService.getMessage(EMPTY_FIELD)));
                break;
            }
            default:
                break;
        }
    }
}
