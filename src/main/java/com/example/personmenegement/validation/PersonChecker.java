package com.example.personmenegement.validation;

import com.example.personmenegement.soap.person.Person;
import com.example.personmenegement.soap.person.ServiceStatus;
import com.example.personmenegement.types.Position;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PersonChecker {
    public void checkPersonRequiredFields(Person person) {

        if (person.getName().equals("")) {// todo а если null?
            person.setName("Поле не заполнено");// todo используй ResourceBundle чтобы брать сообщения из property
            person.setValid(false);
        }
        if (person.getSalary().equals("")) {// todo а если null?
            person.setSalary("Поле не заполнено");// todo используй ResourceBundle чтобы брать сообщения из property
            person.setValid(false);
        }
        if (person.getAge().equals("")) {// todo а если null?
            person.setAge("Поле не заполнено");// todo используй ResourceBundle чтобы брать сообщения из property
            person.setValid(false);
        }
        if (person.getPosition().equals("")) {// todo а если null?
            person.setPosition("Поле не заполнено");// todo используй ResourceBundle чтобы брать сообщения из property
            person.setValid(false);
        }
        if (person.getExperience().equals("")) {// todo а если null?
            person.setPosition("Поле не заполнено");// todo используй ResourceBundle чтобы брать сообщения из property
            person.setValid(false);
        }
    }

    public ServiceStatus checkPersonSalary(Person person) {
        Position positionPers = Position.valueOf(person.getPosition());
        BigDecimal salaryPers = new BigDecimal(person.getSalary());
        ServiceStatus serviceStatus = new ServiceStatus();
        if (positionPers.getSalaryMin().compareTo(salaryPers) == 1 // todo лучше укажи > 0 + лучше вынести в отдельный метод с информативным названием чтобы понимать что тут вычисляется
                || positionPers.getSalaryMax().compareTo(salaryPers) == -1) {// todo лучше укажи < 0 + лучше вынести в отдельный метод с информативным названием чтобы понимать что тут вычисляется
            person.setValid(false);
            serviceStatus.setMessage("ЗП " + positionPers.toString()// todo toString необязателен
                    + " должна быть в диапазоне от " + positionPers.getSalaryMin()// todo используй ResourceBundle чтобы брать сообщения из property
                    + " до " + positionPers.getSalaryMax());
        }
        return serviceStatus;
    }

    public ServiceStatus checkPersonExperienceForPosition(Person person) {
        Position positionPers = Position.valueOf(person.getPosition());
        ServiceStatus serviceStatus = new ServiceStatus();
        if (positionPers.getWorkExperience().compareTo(Double.valueOf(person.getExperience())) == 1) { // todo лучше укажи > 0 + лучше вынести в отдельный метод с информативным названием чтобы понимать что тут вычисляется
            // todo используй ResourceBundle чтобы брать сообщения из property
            person.setValid(false);
            serviceStatus.setMessage("Стаж работы должен быть не менее "
                    + positionPers.getWorkExperience() + " лет для позиции " + positionPers.toString());// todo toString необязателен
        }
        return serviceStatus;
    }
}
