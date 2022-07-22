package com.example.personmenegementrest.services.validation;


import com.example.personmenegementrest.api.PersonChecker;
import com.example.personmenegementrest.dto.Person;
import com.example.personmenegementrest.types.Position;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ResourceBundle;

@Slf4j
@Service
public class PersonCheckerImp implements PersonChecker {
    private static final String EMPTY_FIELD = "emptyField";
    private static final String INCORRECT_AGE = "incorrectAge";
    private static final String INCORRECT_POSITION = "incorrectPosition";
    private static final String INCORRECT_SALARY = "incorrectSalary";
    private static final String LITTLE_WORK_EXPERIENCE = "littleWorkExperience";
    private final ResourceBundle errorMsg = ResourceBundle.getBundle("message");

    public void checkPersonRequiredFields(Person person) {
        log.info("Was calling checkPersonRequiredFields. Input person: " + person.toString());
        if (person.getName() == null || person.getName().trim().equals("")) {
            person.setName(errorMsg.getString(EMPTY_FIELD));
            person.setValid(false);
        }
        if (person.getSalary() == null || person.getSalary().equals("")) {
            person.setSalary(errorMsg.getString(EMPTY_FIELD));
            person.setValid(false);
        }
        if (person.getAge() == null || person.getAge().equals("")) {
            person.setAge(errorMsg.getString(EMPTY_FIELD));
            person.setValid(false);
        }
        if (person.getAge() != null && Integer.parseInt(person.getAge()) < 16) {
            person.setAge(errorMsg.getString(INCORRECT_AGE));
            person.setValid(false);
        }
        if (person.getPosition() == null || person.getPosition().equals("")) {
            person.setPosition(errorMsg.getString(EMPTY_FIELD));
            person.setValid(false);
        }
        if (person.getPosition() != null && Position.getDefine(person.getPosition()) == Position.UNDEFINED) {
            person.setPosition(errorMsg.getString(INCORRECT_POSITION));
            person.setValid(false);
        }
        if (person.getExperience() == null || person.getExperience().equals("")) {
            person.setExperience(errorMsg.getString(EMPTY_FIELD));
            person.setValid(false);
        }
    }


    public void checkPersonSalary(Person person) {
        log.info("Was calling checkPersonSalary. Input person: " + person.toString());
        Position positionPers = Position.getDefine(person.getPosition());
        if (!checkSalaryMatchingPosition(positionPers, new BigDecimal(person.getSalary()))) {
            person.setValid(false);
            person.setSalary(MessageFormat.format(errorMsg.getString(INCORRECT_SALARY),
                    positionPers,
                    positionPers.getSalaryMin(),
                    positionPers.getSalaryMax()));
        }
    }

    public void checkPersonExperienceForPosition(Person person) {
        log.info("Was calling checkPersonExperienceForPosition. Input person: " + person.toString());
        Position positionPerson = Position.getDefine(person.getPosition());
        if (!checkExperienceMatchingPosition(positionPerson, person.getExperience())) {
            person.setValid(false);
            person.setExperience(MessageFormat.format(errorMsg.getString(LITTLE_WORK_EXPERIENCE),
                    positionPerson.getWorkExperience(),
                    positionPerson.toString()));
        }
    }

    private boolean checkSalaryMatchingPosition(Position positionPerson, BigDecimal salaryPerson) {
        log.debug("Was calling checkSalaryMatchingPosition. Input positionPerson: "
                + positionPerson.toString() + " salaryPerson: " + salaryPerson);
        if (positionPerson.getSalaryMin().compareTo(salaryPerson) > 0
                || positionPerson.getSalaryMax().compareTo(salaryPerson) < 0) {
            return false;
        }
        return true;
    }

    private boolean checkExperienceMatchingPosition(Position positionPerson, String experience) {
        log.debug("Was calling checkSalaryMatchingPosition. Input positionPerson: "
                + positionPerson.toString() + " experience: " + experience);
        if (positionPerson.getWorkExperience().compareTo(Double.valueOf(experience)) > 0) {
            return false;
        }
        return true;
    }
}
