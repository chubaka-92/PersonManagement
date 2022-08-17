package com.example.personmenegement.services.validation.cheker;

import com.example.personmenegement.api.MessageService;
import com.example.personmenegement.api.PersonChecker;
import com.example.personmenegement.dto.PersonDto;
import com.example.personmenegement.types.Position;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.personmenegement.types.PersonFieldName.*;

@Slf4j
@RequiredArgsConstructor
public class PersonCheckerImp implements PersonChecker {
    private static final String INCORRECT_AGE = "incorrectAge";
    private static final String INCORRECT_POSITION = "incorrectPosition";
    private static final String INCORRECT_SALARY = "incorrectSalary";
    private static final String LITTLE_WORK_EXPERIENCE = "littleWorkExperience";
    private static final int WORK_MIN_AGE = 16;
    private final MessageService messageService;

    public List<String> checkRequiredFields(PersonDto personDto) {
        log.info("Was calling checkRequiredFields. Input person: {}", personDto);
        List<String> invalidFields = new ArrayList<>();
        if (personDto.getName() == null || personDto.getName().isBlank()) {
            invalidFields.add(NAME);
        }
        if (personDto.getPosition() == null || personDto.getPosition().isBlank()) {
            invalidFields.add(POSITION);
        }
        if (personDto.getAge() == null || personDto.getAge().isBlank()) {
            invalidFields.add(AGE);
        }
        if (personDto.getSalary() == null || personDto.getSalary().isBlank()) {
            invalidFields.add(SALARY);
        }
        if (personDto.getExperience() == null || personDto.getExperience().isBlank()) {
            invalidFields.add(EXPERIENCE);
        }
        return invalidFields;
    }

    public Map<String, String> checkAge(String age) {
        log.info("Was calling checkAge. Input age: {}", age);
        Map<String, String> response = new HashMap<>();
        if (age != null && Long.parseLong(age) < WORK_MIN_AGE) {
            String message = messageService.getMessage(INCORRECT_AGE);
            response.put(AGE, message);
        }
        return response;
    }

    public Map<String, String> checkSalary(Position position, String salary) {
        log.info("Was calling checkSalary. Input position: {}, salary: {}", position, salary);
        Map<String, String> response = new HashMap<>();
        if (!checkSalaryMatchingPosition(position, new BigDecimal(salary))) {
            String message = MessageFormat.format(
                    messageService.getMessage(INCORRECT_SALARY),
                    messageService.getMessage(position),
                    position.getSalaryMin(),
                    position.getSalaryMax());
            response.put(SALARY, message);
        }
        return response;
    }

    public Map<String, String> checkPosition(String position) {
        log.info("Was calling checkPosition. Input position: {}", position);
        Map<String, String> response = new HashMap<>();
        if (Position.definePosition(position) == Position.UNDEFINED) {
            String message = messageService.getMessage(INCORRECT_POSITION);
            response.put(POSITION, message);
        }
        return response;
    }

    public Map<String, String> checkExperience(Position position, String experience) {
        log.info("Was calling checkExperience. Input position: {}, experience: {}", position, experience);
        Map<String, String> response = new HashMap<>();
        if (!checkExperienceMatchingPosition(position, experience)) {
            String message = MessageFormat.format(
                    messageService.getMessage(LITTLE_WORK_EXPERIENCE),
                    position.getWorkExperience(),
                    messageService.getMessage(position));
            response.put(EXPERIENCE, message);
        }
        return response;
    }

    private boolean checkExperienceMatchingPosition(Position positionPerson, String experience) {
        log.info("Was calling checkExperienceMatchingPosition. Input positionPerson: {} experience: {}",
                positionPerson,
                experience);
        return positionPerson.getWorkExperience().compareTo(Double.valueOf(experience)) <= 0;
    }

    private boolean checkSalaryMatchingPosition(Position positionPerson, BigDecimal salaryPerson) {
        log.info("Was calling checkSalaryMatchingPosition. Input positionPerson: {} salaryPerson: {}",
                positionPerson,
                salaryPerson);
        return positionPerson.getSalaryMin().compareTo(salaryPerson) <= 0
                && positionPerson.getSalaryMax().compareTo(salaryPerson) >= 0;
    }
}
