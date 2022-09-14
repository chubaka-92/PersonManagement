package com.example.personmanagement.api.person;

import com.example.personmanagement.dto.PersonDto;
import com.example.personmanagement.types.Position;

import java.util.List;
import java.util.Map;

public interface PersonChecker {

    List<String> checkRequiredFields(PersonDto personDto);

    Map<String, String> checkAge(String age);

    Map<String, String> checkSalary(Position position, String salary);

    Map<String, String> checkPosition(String position);

    Map<String, String> checkExperience(Position position, String experience);
}
