package com.example.personmenegement.validation;

import com.example.personmenegement.config.api.PersonChecker;
import com.example.personmenegement.soap.person.Person;
import com.example.personmenegement.soap.person.ServiceStatus;
import com.example.personmenegement.types.Position;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ResourceBundle;

@Service
public class PersonCheckerImp implements PersonChecker {
    public static final String EMPTY_FIELD = "emptyField";
    public static final String INCORRECT_AGE = "incorrectAge";
    public static final String INCORRECT_POSITION = "incorrectPosition";
    public static final String INCORRECT_SALARY = "incorrectSalary";
    public static final String LITTLE_WORK_EXPERIENCE = "littleWorkExperience";
    private final ResourceBundle errorMsg = ResourceBundle.getBundle("message");

    public void checkPersonRequiredFields(Person person) {

        if (person.getName() == null || person.getName().trim().equals("")) {// todo вторая проверка лишняя  //  DONE
            person.setName(errorMsg.getString(EMPTY_FIELD));// todo вынести в константу
            person.setValid(false);                         //  done
        }
        if (person.getSalary() == null || person.getSalary().equals("")) {
            person.setSalary(errorMsg.getString(EMPTY_FIELD));// todo вынести в константу
            person.setValid(false);                           //  done
        }
        if (person.getAge() == null || person.getAge().equals("")) {
            person.setAge(errorMsg.getString(EMPTY_FIELD));// todo вынести в константу
            person.setValid(false);                        //  done
        }
        if (person.getAge() != null && Integer.parseInt(person.getAge()) < 16) {
            person.setAge(errorMsg.getString(INCORRECT_AGE));// todo вынести в константу
            person.setValid(false);                          //  done
        }
        if (person.getPosition() == null || person.getPosition().equals("")) {
            person.setPosition(errorMsg.getString(EMPTY_FIELD));// todo вынести в константу
            person.setValid(false);                             //  done
        }
        if (person.getPosition() != null && Position.getDefine(person.getPosition()) == Position.UNDEFINED) {
            person.setPosition(errorMsg.getString(INCORRECT_POSITION));// todo вынести в константу
            person.setValid(false);                                    //  done
        }
        if (person.getExperience() == null || person.getExperience().equals("")) {
            person.setExperience(errorMsg.getString(EMPTY_FIELD));// todo вынести в константу
            person.setValid(false);                               //  done
        }
    }

    public ServiceStatus checkPersonSalary(Person person) {
        Position positionPers = Position.getDefine(person.getPosition());
        ServiceStatus serviceStatus = new ServiceStatus();
        if (!checkSalaryMatchingPosition(positionPers, new BigDecimal(person.getSalary()))) {
            person.setValid(false);
            // todo вынести в константу
            //  done
            serviceStatus.setMessage(MessageFormat.format(errorMsg.getString(INCORRECT_SALARY),
                    positionPers,
                    positionPers.getSalaryMin(),
                    positionPers.getSalaryMax()));
        }
        return serviceStatus;
    }

    public ServiceStatus checkPersonExperienceForPosition(Person person) {
        Position positionPerson = Position.getDefine(person.getPosition());
        ServiceStatus serviceStatus = new ServiceStatus();
        if (!checkExperienceMatchingPosition(positionPerson, person.getExperience())) {
            person.setValid(false);
            serviceStatus.setMessage(MessageFormat.format(errorMsg.getString(LITTLE_WORK_EXPERIENCE),// todo вынести в константу
                    positionPerson.getWorkExperience(),                                              //  done
                    positionPerson.toString()));
        }
        return serviceStatus;
    }

    // todo лучше давать полное имя переменным - positionPerson
    //  done
    private boolean checkSalaryMatchingPosition(Position positionPerson, BigDecimal salaryPerson) {
        if (positionPerson.getSalaryMin().compareTo(salaryPerson) > 0
                || positionPerson.getSalaryMax().compareTo(salaryPerson) < 0) {
            return false;
        }
        return true;
    }

    // todo лучше давать полное имя переменным - positionPerson
    //  done
    private boolean checkExperienceMatchingPosition(Position positionPerson, String experience) {
        if (positionPerson.getWorkExperience().compareTo(Double.valueOf(experience)) > 0) {
            return false;
        }
        return true;
    }
}
