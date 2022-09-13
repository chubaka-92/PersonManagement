package com.example.personmanagement.api.user;

import com.example.personmanagement.dto.UserDto;

public interface UserValidation {

    UserDto validate(UserDto userDto);
}
