package com.example.personmanagement.services.validation.cheker;

import com.example.personmanagement.api.checker.PersonChecker;
import com.example.personmanagement.dto.PersonDto;
import com.example.personmanagement.services.MessageService;
import com.example.personmanagement.types.Position;
import com.example.personmanagement.types.Positions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.personmanagement.types.PersonFieldName.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class PersonCheckerImp implements PersonChecker {
    private static final String INCORRECT_AGE = "incorrectAge";
    private static final String INCORRECT_POSITION = "incorrectPosition";
    private static final String INCORRECT_SALARY = "incorrectSalary";
    private static final String LITTLE_WORK_EXPERIENCE = "littleWorkExperience";
    private static final int WORK_MIN_AGE = 16;
    private final MessageService messageService;
    private final Positions positions;

    public List<String> checkRequiredFields(PersonDto personDto) {
        log.info("Was calling checkRequiredFields. Input person: {}", personDto);
        List<String> invalidFields = new ArrayList<>();
        if (checkingBadValueName(personDto)) { //todo сделать приватный метод на проверку на null и isBlank  //  DONE
            invalidFields.add(NAME);
        }
        if (checkingBadValuePosition(personDto)) { //todo сделать приватный метод на проверку на null и isBlank  //  DONE
            invalidFields.add(POSITION);
        }
        if (checkingBadValueAge(personDto)) { //todo сделать приватный метод на проверку на null и isBlank  //  DONE
            invalidFields.add(AGE);
        }
        if (checkingBadValueSalary(personDto)) { //todo сделать приватный метод на проверку на null и isBlank  //  DONE
            invalidFields.add(SALARY);
        }
        if (checkingBadValueExperience(personDto)) { //todo сделать приватный метод на проверку на null и isBlank  //  DONE
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
        if (positions.getPosition(position) == Position.UNDEFINED) {
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

    private boolean checkingBadValueExperience(PersonDto personDto) {
        log.info("Was calling checkingBadValueExperience. Input personDto: {}", personDto);
        return (personDto.getExperience() == null || personDto.getExperience().isBlank());
    }

    private boolean checkingBadValueSalary(PersonDto personDto) {
        log.info("Was calling checkingBadValueSalary. Input personDto: {} ", personDto);
        return (personDto.getSalary() == null || personDto.getSalary().isBlank());
    }

    private boolean checkingBadValueAge(PersonDto personDto) {
        log.info("Was calling checkingBadValueAge. Input personDto: {} ", personDto);
        return (personDto.getAge() == null || personDto.getAge().isBlank());
    }

    private boolean checkingBadValuePosition(PersonDto personDto) {
        log.info("Was calling checkingBadValuePosition. Input personDto: {}", personDto);
        return (personDto.getPosition() == null || personDto.getPosition().isBlank());
    }

    private boolean checkingBadValueName(PersonDto personDto) {
        log.info("Was calling checkingBadValueName. Input personDto: {}", personDto);
        return personDto.getName() == null || personDto.getName().isBlank();
    }
}
