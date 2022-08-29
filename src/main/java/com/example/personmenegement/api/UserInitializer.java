package com.example.personmenegement.api;

import com.example.personmenegement.dto.UserDto;

import java.util.List;
import java.util.Map;

public interface UserInitializer {
    void addFieldsEmpty(List<String> incorrectFields);

    void addIncorrectArgumentMessage(Map<String, String> incorrectArguments);

    boolean hasErrors();

    UserDto getUserDtoError();
}
