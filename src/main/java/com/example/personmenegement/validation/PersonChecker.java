package com.example.personmenegement.validation;

import com.example.personmenegement.soap.person.Person;
import com.example.personmenegement.soap.person.ServiceStatus;
import com.example.personmenegement.types.Position;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PersonChecker {
    public void checkPersonRequiredFields(Person person) {

        if (person.getName().equals("")) {
            person.setName("Поле не заполнено");
            person.setValid(false);
        }
        if (person.getSalary().equals("")) {
            person.setSalary("Поле не заполнено");
            person.setValid(false);
        }
        if (person.getAge().equals("")) {
            person.setAge("Поле не заполнено");
            person.setValid(false);
        }
        if (person.getPosition().equals("")) {
            person.setPosition("Поле не заполнено");
            person.setValid(false);
        }
        if (person.getExperience().equals("")) {
            person.setPosition("Поле не заполнено");
            person.setValid(false);
        }
    }

    public ServiceStatus checkPersonSalary(Person person) {
        Position positionPers = Position.valueOf(person.getPosition());
        BigDecimal salaryPers = new BigDecimal(person.getSalary());
        ServiceStatus serviceStatus = new ServiceStatus();
        if (positionPers.getSalaryMin().compareTo(salaryPers) == 1
                || positionPers.getSalaryMax().compareTo(salaryPers) == -1) {
            person.setValid(false);
            serviceStatus.setMessage("ЗП " + positionPers.toString()
                    + " должна быть в диапазоне от " + positionPers.getSalaryMin()
                    + " до " + positionPers.getSalaryMax());
        }
        return serviceStatus;
    }

    public ServiceStatus checkPersonExperienceForPosition(Person person) {
        Position positionPers = Position.valueOf(person.getPosition());
        ServiceStatus serviceStatus = new ServiceStatus();
        if (positionPers.getWorkExperience().compareTo(Double.valueOf(person.getExperience())) == 1) {
            person.setValid(false);
            serviceStatus.setMessage("Стаж работы должен быть не менее "
                    + positionPers.getWorkExperience() + " лет для позиции " + positionPers.toString());
        }
        return serviceStatus;
    }
}
