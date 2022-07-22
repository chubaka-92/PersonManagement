package com.example.personmenegement.services.validation;

import com.example.personmenegement.api.PersonChecker;
import com.example.personmenegement.soap.person.Person;
import com.example.personmenegement.soap.person.ServiceStatus;
import com.example.personmenegement.types.Position;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ResourceBundle;

@Service
public class PersonCheckerImp implements PersonChecker {
    private static final String EMPTY_FIELD = "emptyField";
    private static final String INCORRECT_AGE = "incorrectAge";
    private static final String INCORRECT_POSITION = "incorrectPosition";
    private static final String INCORRECT_SALARY = "incorrectSalary";
    private static final String LITTLE_WORK_EXPERIENCE = "littleWorkExperience";
    private final ResourceBundle errorMsg = ResourceBundle.getBundle("message"); //todo ResourceBundle используется PersonServiceImp, сделать отдельный сервис работающий с ResourceBundle

    public void checkPersonRequiredFields(Person person) {

        if (person.getName() == null || person.getName().trim().equals("")) {
            person.setName(errorMsg.getString(EMPTY_FIELD));
            person.setValid(false);
        }
        if (person.getSalary() == null || person.getSalary().equals("")) {
            person.setSalary(errorMsg.getString(EMPTY_FIELD));
        }
        if (person.getAge() == null || person.getAge().equals("")) {
            person.setAge(errorMsg.getString(EMPTY_FIELD));
        }
        if (person.getAge() != null && Integer.parseInt(person.getAge()) < 16) {
            person.setAge(errorMsg.getString(INCORRECT_AGE));
        }
        if (person.getPosition() == null || person.getPosition().equals("")) {
            person.setPosition(errorMsg.getString(EMPTY_FIELD));
        }
        if (person.getPosition() != null && Position.getDefine(person.getPosition()) == Position.UNDEFINED) {
            person.setPosition(errorMsg.getString(INCORRECT_POSITION));
        }
        if (person.getExperience() == null || person.getExperience().equals("")) {
            person.setExperience(errorMsg.getString(EMPTY_FIELD));
        }
    }

    public ServiceStatus checkPersonSalary(Person person) {
        Position positionPers = Position.getDefine(person.getPosition()); //todo лучше не сокращать название переменных
        ServiceStatus serviceStatus = new ServiceStatus();
        if (!checkSalaryMatchingPosition(positionPers, new BigDecimal(person.getSalary()))) {
            person.setValid(false); //todo скрытая логика в этом методе. Т.е. я ожидаю, что будет какой то чек, а тут еще есть проставление значений в обьект person
            serviceStatus.setMessage(MessageFormat.format(errorMsg.getString(INCORRECT_SALARY), //todo в данном случае если начал делать перенос аргументов, то нужно переносить все аргументы
                    positionPers,
                    positionPers.getSalaryMin(),
                    positionPers.getSalaryMax()));
        }
        return serviceStatus;
    }

    public ServiceStatus checkPersonExperienceForPosition(Person person) {
        Position positionPerson = Position.getDefine(person.getPosition()); //todo см на туду выше. также тут получается, что нарушается стиль написания. в одном случае пишешь кратко, в другом полное название переменной
        ServiceStatus serviceStatus = new ServiceStatus();
        if (!checkExperienceMatchingPosition(positionPerson, person.getExperience())) {
            person.setValid(false); //todo скрытая логика в этом методе. Т.е. я ожидаю, что будет какой то чек, а тут еще есть проставление значений в обьект person
            serviceStatus.setMessage(MessageFormat.format(errorMsg.getString(LITTLE_WORK_EXPERIENCE), //todo в данном случае если начал делать перенос аргументов, то нужно переносить все аргументы
                    positionPerson.toString()));
        }
        return serviceStatus;
    }

    private boolean checkSalaryMatchingPosition(Position positionPerson, BigDecimal salaryPerson) { //todo логику проверки зп лучше реализовать в enum
        if (positionPerson.getSalaryMin().compareTo(salaryPerson) > 0
                || positionPerson.getSalaryMax().compareTo(salaryPerson) < 0) {
            return false;
        }
        return true;
    }

    private boolean checkExperienceMatchingPosition(Position positionPerson, String experience) {
        if (positionPerson.getWorkExperience().compareTo(Double.valueOf(experience)) > 0) { //todo логику проверки опыта лучше реализовать в enum
            return false;
        }
        return true;
    }
}
