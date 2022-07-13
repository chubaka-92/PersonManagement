package com.example.personmenegement.validation;

import com.example.personmenegement.soap.person.Person;
import com.example.personmenegement.soap.person.ServiceStatus;
import com.example.personmenegement.types.Position;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ResourceBundle;

@Service
public class PersonChecker {// todo добавить интерфейс (все интерфейсы в пакете api)
    private final ResourceBundle errorMsg = ResourceBundle.getBundle("message");

    public void checkPersonRequiredFields(Person person) {

        if (person.getName() == null || person.getName().equals("") || person.getName().trim().equals("")) {// todo вторая проверка лишняя
            person.setName(errorMsg.getString("emptyField"));// todo вынести в константу
            person.setValid(false);
        }
        if (person.getSalary() == null || person.getSalary().equals("")) {
            person.setSalary(errorMsg.getString("emptyField"));// todo вынести в константу
            person.setValid(false);
        }
        if (person.getAge() == null || person.getAge().equals("")) {
            person.setAge(errorMsg.getString("emptyField"));// todo вынести в константу
            person.setValid(false);
        }
        if (person.getAge() != null && Integer.parseInt(person.getAge()) < 16) {
            person.setAge(errorMsg.getString("incorrectAge"));// todo вынести в константу
            person.setValid(false);
        }
        if (person.getPosition() == null || person.getPosition().equals("")) {
            person.setPosition(errorMsg.getString("emptyField"));// todo вынести в константу
            person.setValid(false);
        }
        if (person.getPosition() != null && Position.getDefine(person.getPosition())==Position.DONT_UNDERSTEND) {
            person.setPosition(errorMsg.getString("incorrectPosition"));// todo вынести в константу
            person.setValid(false);
        }
        if (person.getExperience() == null || person.getExperience().equals("")) {
            person.setExperience(errorMsg.getString("emptyField"));// todo вынести в константу
            person.setValid(false);
        }
    }

    public ServiceStatus checkPersonSalary(Person person) {
        Position positionPers = Position.getDefine(person.getPosition());
        ServiceStatus serviceStatus = new ServiceStatus();
        if (!checkSalaryMatchingPosition(positionPers, new BigDecimal(person.getSalary()))) {
            person.setValid(false);
            // todo вынести в константу
            serviceStatus.setMessage(MessageFormat.format(errorMsg.getString("incorrectSalary"),
                    positionPers,
                    positionPers.getSalaryMin(),
                    positionPers.getSalaryMax()));
        }
        return serviceStatus;
    }

    public ServiceStatus checkPersonExperienceForPosition(Person person) {
        Position positionPers = Position.getDefine(person.getPosition());
        ServiceStatus serviceStatus = new ServiceStatus();
        if (!checkExperienceMatchingPosition(positionPers, person.getExperience())) {
            person.setValid(false);
            serviceStatus.setMessage(MessageFormat.format(errorMsg.getString("littleWorkExperience"),// todo вынести в константу
                    positionPers.getWorkExperience(),
                    positionPers.toString()));
        }
        return serviceStatus;
    }

    // todo лучше давать полное имя переменным - positionPerson
    private boolean checkSalaryMatchingPosition(Position positionPers, BigDecimal salaryPers) {
        if (positionPers.getSalaryMin().compareTo(salaryPers) > 0
                || positionPers.getSalaryMax().compareTo(salaryPers) < 0) {
            return false;
        }
        return true;
    }

    // todo лучше давать полное имя переменным - positionPerson
    private boolean checkExperienceMatchingPosition(Position positionPers, String experience) {
        if (positionPers.getWorkExperience().compareTo(Double.valueOf(experience)) > 0) {
            return false;
        }
        return true;
    }
}
