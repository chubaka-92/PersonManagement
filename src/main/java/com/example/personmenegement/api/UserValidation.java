package com.example.personmenegement.api;

import com.example.personmenegement.dto.UserDto;

public interface UserValidation {

    UserDto validate(UserDto userDto);
}
