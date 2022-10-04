package com.example.personmanagement.api.validation;

import com.example.personmanagement.dto.UserDto;

public interface UserValidation {

    UserDto validate(UserDto userDto);
}
