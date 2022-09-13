package com.example.personmanagement.api.user;

import com.example.personmanagement.dto.UserDto;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserChecker {

    List<String> checkRequiredFields(UserDto userDto);

    Map<String, String> checkUserName(String username);

    Map<String, String> checkEmail(String email);

    Map<String, String> checkRoles(Set<String> roles);
}
