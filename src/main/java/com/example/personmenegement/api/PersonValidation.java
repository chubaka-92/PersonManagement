package com.example.personmenegement.api;

import com.example.personmenegement.dto.PersonDto;

public interface PersonValidation {

    PersonDto validate(PersonDto personDto);
}
