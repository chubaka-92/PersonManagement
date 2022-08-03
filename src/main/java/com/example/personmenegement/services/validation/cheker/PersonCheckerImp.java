package com.example.personmenegement.services.validation.cheker;

import com.example.personmenegement.api.MessageService;
import com.example.personmenegement.api.PersonChecker;
import com.example.personmenegement.dto.Person;
import com.example.personmenegement.services.MessageServiceImp;
import com.example.personmenegement.types.Position;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.personmenegement.types.PersonFieldName.*;
import static com.example.personmenegement.types.Position.checkExperienceMatchingPosition;
import static com.example.personmenegement.types.Position.checkSalaryMatchingPosition;

@Slf4j
public class PersonCheckerImp implements PersonChecker {
    private static final String INCORRECT_AGE = "incorrectAge";
    private static final String INCORRECT_POSITION = "incorrectPosition";
    private static final String INCORRECT_SALARY = "incorrectSalary";
    private static final String LITTLE_WORK_EXPERIENCE = "littleWorkExperience";
    private static final int WORK_MIN_AGE = 16;
    private final MessageService messageService;

    // todo выбери один стиль, либо писать руками конструктор, либо lombok
    public PersonCheckerImp() {
        this.messageService = new MessageServiceImp();
    }

    public List<String> checkRequiredFields(Person person) {
        log.info("Was calling checkRequiredFields. Input person: {}", person.toString());
        List<String> invalidFields = new ArrayList<>();
        if (person.getName() == null || person.getName().trim().equals("")) {// todo isBlank
            invalidFields.add(NAME);
        }
        if (person.getPosition() == null || person.getPosition().trim().equals("")) {// todo isBlank
            invalidFields.add(POSITION);
        }
        if (person.getAge() == null || person.getAge().trim().equals("")) {// todo isBlank
            invalidFields.add(AGE);
        }
        if (person.getSalary() == null || person.getSalary().trim().equals("")) {// todo isBlank
            invalidFields.add(SALARY);
        }
        if (person.getExperience() == null || person.getExperience().trim().equals("")) {// todo isBlank
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
}
