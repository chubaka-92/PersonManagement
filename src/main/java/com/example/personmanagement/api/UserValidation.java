package com.example.personmanagement.api;

import com.example.personmanagement.dto.UserDto;

public interface UserValidation {

    UserDto validate(UserDto userDto);
}
