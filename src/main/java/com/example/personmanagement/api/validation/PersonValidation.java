package com.example.personmanagement.api.validation;

import com.example.personmanagement.dto.PersonDto;

public interface PersonValidation {

    PersonDto validate(PersonDto personDto);
}
