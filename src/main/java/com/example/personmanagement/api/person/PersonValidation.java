package com.example.personmanagement.api.person;

import com.example.personmanagement.dto.PersonDto;

public interface PersonValidation {

    PersonDto validate(PersonDto personDto);
}
