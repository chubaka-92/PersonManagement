package com.example.personmanagement.api.person;

import com.example.personmanagement.dto.PersonDto;

import java.util.List;
import java.util.Map;

public interface PersonInitializer {

    void addFieldsEmpty(List<String> incorrectFields);

    void addIncorrectArgumentMessage(Map<String, String> incorrectArguments);

    boolean hasErrors();

    PersonDto getPersonDtoError();
}
