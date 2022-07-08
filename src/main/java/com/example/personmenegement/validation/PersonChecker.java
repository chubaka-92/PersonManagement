package com.example.personmenegement.validation;

import com.example.personmenegement.soap.person.Person;
import com.example.personmenegement.soap.person.ServiceStatus;
import com.example.personmenegement.types.Position;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ResourceBundle;

@Service
public class PersonChecker {
    private final ResourceBundle errorMsg = ResourceBundle.getBundle("message");

    public void checkPersonRequiredFields(Person person) {

        if (person.getName() == null || person.getName().equals("")) {// todo а если null? // DONE
            person.setName(errorMsg.getString("emptyField"));// todo используй ResourceBundle чтобы брать сообщения из property
            person.setValid(false); //                                  done
        }
        if (person.getSalary() == null || person.getSalary().equals("")) {// todo а если null? // DONE
            person.setSalary(errorMsg.getString("emptyField"));// todo используй ResourceBundle чтобы брать сообщения из property
            person.setValid(false);//                                  done
        }
        if (person.getAge() == null || person.getAge().equals("")) {// todo а если null? // DONE
            person.setAge(errorMsg.getString("emptyField"));// todo используй ResourceBundle чтобы брать сообщения из property
            person.setValid(false);//                                  done
        }
        if (person.getPosition() == null || person.getPosition().equals("")) {// todo а если null? // DONE
            person.setPosition(errorMsg.getString("emptyField"));// todo используй ResourceBundle чтобы брать сообщения из property
            person.setValid(false);//                                  done
        }
        if (person.getExperience() == null || person.getExperience().equals("")) {// todo а если null? // DONE
            person.setExperience(errorMsg.getString("emptyField"));// todo используй ResourceBundle чтобы брать сообщения из property
            person.setValid(false);//                                  done
        }
    }

    public ServiceStatus checkPersonSalary(Person person) {
        Position positionPers = Position.valueOf(person.getPosition());
        ServiceStatus serviceStatus = new ServiceStatus();
        if (!checkSalaryMatchingPosition(positionPers, new BigDecimal(person.getSalary()))) {
            person.setValid(false);
            // todo используй ResourceBundle чтобы брать сообщения из property
            //  done
            serviceStatus.setMessage(MessageFormat.format(errorMsg.getString("incorrectSalary"),
                    positionPers,
                    positionPers.getSalaryMin(),
                    positionPers.getSalaryMax()));// todo toString необязателен
            //    done
        }
        return serviceStatus;
    }

    public ServiceStatus checkPersonExperienceForPosition(Person person) {
        Position positionPers = Position.valueOf(person.getPosition());
        ServiceStatus serviceStatus = new ServiceStatus();
        if (!checkExperienceMatchingPosition(positionPers, person.getExperience())) { // todo лучше укажи > 0 + лучше вынести в отдельный метод с информативным названием чтобы понимать что тут вычисляется
            //                                                                                   done
            // todo используй ResourceBundle чтобы брать сообщения из property
            //  done
            person.setValid(false);
            serviceStatus.setMessage(MessageFormat.format(errorMsg.getString("littleWorkExperience"),
                    positionPers.getWorkExperience(),
                    positionPers.toString()));// todo toString необязателен
        }                                    //   done
        return serviceStatus;
    }

    private boolean checkSalaryMatchingPosition(Position positionPers, BigDecimal salaryPers) {
        if (positionPers.getSalaryMin().compareTo(salaryPers) > 0  // todo лучше укажи > 0 + лучше вынести в отдельный метод с информативным названием чтобы понимать что тут вычисляется  // DONE
                || positionPers.getSalaryMax().compareTo(salaryPers) < 0) {// todo лучше укажи < 0 + лучше вынести в отдельный метод с информативным названием чтобы понимать что тут вычисляется  // DONE
            return false;
        }
        return true;
    }

    private boolean checkExperienceMatchingPosition(Position positionPers, String experience) {
        if (positionPers.getWorkExperience().compareTo(Double.valueOf(experience)) > 0) {
            return false;
        }
        return true;
    }
}
