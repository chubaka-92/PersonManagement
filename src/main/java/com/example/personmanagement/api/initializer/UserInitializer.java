package com.example.personmanagement.api.initializer;

import com.example.personmanagement.dto.UserDto;

import java.util.List;
import java.util.Map;

public interface UserInitializer {
    void addFieldsEmpty(List<String> incorrectFields);

    void addIncorrectArgumentMessage(Map<String, String> incorrectArguments);

    boolean hasErrors();

    UserDto getUserDtoError();

    void setUserDtoError(UserDto userDto);
}
