package com.example.personmenegement.services.validation.cheker;

import com.example.personmenegement.api.PersonChecker;
import com.example.personmenegement.api.ResourceBundleService;
import com.example.personmenegement.services.ResourceBundleServiceImp;
import com.example.personmenegement.soap.person.Person;
import com.example.personmenegement.types.Position;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.personmenegement.types.PersonFieldName.*;
import static com.example.personmenegement.types.Position.checkExperienceMatchingPosition;
import static com.example.personmenegement.types.Position.checkSalaryMatchingPosition;

@Service
public class PersonCheckerImp implements PersonChecker {
    private static final String INCORRECT_AGE = "incorrectAge";
    private static final String INCORRECT_POSITION = "incorrectPosition";
    private static final String INCORRECT_SALARY = "incorrectSalary";
    private static final String LITTLE_WORK_EXPERIENCE = "littleWorkExperience";
    private static final int WORK_MIN_AGE = 16;
    private final ResourceBundleService messageService;

    public PersonCheckerImp() {
        this.messageService = new ResourceBundleServiceImp();
    }

    public List<String> checkRequiredFields(Person person) {
        List<String> invalidFields = new ArrayList<>();
        if (person.getName() == null || person.getName().trim().equals("")) {
            invalidFields.add(NAME);
        }
        if (person.getPosition() == null || person.getPosition().equals("")) {
            invalidFields.add(POSITION);
        }
        if (person.getAge() == null || person.getAge().equals("")) {
            invalidFields.add(AGE);
        }
        if (person.getSalary() == null || person.getSalary().equals("")) {
            invalidFields.add(SALARY);
        }
        if (person.getExperience() == null || person.getExperience().equals("")) {
            invalidFields.add(EXPERIENCE);
        }
        return invalidFields;
    }

    public Map<String, String> checkAge(String age) {
        Map<String, String> response = new HashMap<>();
        if (age != null && Long.parseLong(age) < WORK_MIN_AGE) {
            String message = messageService.getString(INCORRECT_AGE);
            response.put(AGE, message);
        }
        return response;
    }

    public Map<String, String> checkSalary(Position position, String salary) {
        Map<String, String> response = new HashMap<>();
        if (!checkSalaryMatchingPosition(position, new BigDecimal(salary))) {
            String message = MessageFormat.format(
                    messageService.getString(INCORRECT_SALARY),
                    position.getTranslation(),
                    position.getSalaryMin(),
                    position.getSalaryMax());
            response.put(SALARY, message);
        }
        return response;
    }

    public Map<String, String> checkPosition(String position) {
        Map<String, String> response = new HashMap<>();
        if (Position.getDefine(position) == Position.UNDEFINED) {
            String message = messageService.getString(INCORRECT_POSITION);
            response.put(POSITION, message);
        }
        return response;
    }

    public Map<String, String> checkExperience(Position position, String experience) {
        Map<String, String> response = new HashMap<>();
        if (!checkExperienceMatchingPosition(position, experience)) {
            String message = MessageFormat.format(
                    messageService.getString(LITTLE_WORK_EXPERIENCE),
                    position.getWorkExperience(),
                    position.getTranslation());
            response.put(EXPERIENCE, message);
        }
        return response;
    }
}
