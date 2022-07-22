package com.example.personmenegement.services.validation;

import com.example.personmenegement.api.PersonChecker;
import com.example.personmenegement.api.ResourceBundleService;
import com.example.personmenegement.services.ResourceBundleServiceImp;
import com.example.personmenegement.soap.person.Person;
import com.example.personmenegement.soap.person.ServiceStatus;
import com.example.personmenegement.types.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ResourceBundle;

@Service
@RequiredArgsConstructor
public class PersonCheckerImp implements PersonChecker {
    private static final String EMPTY_FIELD = "emptyField";
    private static final String INCORRECT_AGE = "incorrectAge";
    private static final String INCORRECT_POSITION = "incorrectPosition";
    private static final String INCORRECT_SALARY = "incorrectSalary";
    private static final String LITTLE_WORK_EXPERIENCE = "littleWorkExperience";
    //todo ResourceBundle используется PersonServiceImp, сделать отдельный сервис работающий с ResourceBundle
    //  Done
    private final ResourceBundleService errorMsg;

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
        Position positionPerson = Position.getDefine(person.getPosition()); //todo лучше не сокращать название переменных  //  DONE
        ServiceStatus serviceStatus = new ServiceStatus();
        if (!Position.checkSalaryMatchingPosition(positionPerson, new BigDecimal(person.getSalary()))) {
            person.setValid(false); //todo скрытая логика в этом методе. Т.е. я ожидаю, что будет какой то чек, а тут еще есть проставление значений в обьект person
            serviceStatus.setMessage(MessageFormat.format(
                    errorMsg.getString(INCORRECT_SALARY), //todo в данном случае если начал делать перенос аргументов, то нужно переносить все аргументы
                    positionPerson,                       //  Done
                    positionPerson.getSalaryMin(),
                    positionPerson.getSalaryMax()));
        }
        return serviceStatus;
    }

    public ServiceStatus checkPersonExperienceForPosition(Person person) {
        Position positionPerson = Position.getDefine(person.getPosition()); //todo см на туду выше. также тут получается, что нарушается стиль написания. в одном случае пишешь кратко, в другом полное название переменной
        ServiceStatus serviceStatus = new ServiceStatus();                  // Done
        if (!Position.checkExperienceMatchingPosition(positionPerson, person.getExperience())) {
            person.setValid(false); //todo скрытая логика в этом методе. Т.е. я ожидаю, что будет какой то чек, а тут еще есть проставление значений в обьект person
            serviceStatus.setMessage(MessageFormat.format(
                    errorMsg.getString(LITTLE_WORK_EXPERIENCE), //todo в данном случае если начал делать перенос аргументов, то нужно переносить все аргументы
                    positionPerson.toString()));                //   Done
        }
        return serviceStatus;
    }
    //todo логику проверки зп лучше реализовать в enum
    //  Done
    //todo логику проверки опыта лучше реализовать в enum
    //  Done

}
