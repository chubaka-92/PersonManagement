package com.example.personmenegement.services.validation;

import com.example.personmenegement.api.PersonChecker;
import com.example.personmenegement.api.PersonValidation;
import com.example.personmenegement.soap.person.*;
import com.example.personmenegement.types.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonValidationImp implements PersonValidation {

    private final PersonChecker personChecker;

    public AddPersonResponse addPersonValidator(Person person) {
        personChecker.checkPersonRequiredFields(person);
        ServiceStatus serviceStatus = new ServiceStatus();
        if (person.getValid()) {
            serviceStatus = personChecker.checkPersonSalary(person);
        }
        if (person.getValid()) {
            serviceStatus = personChecker.checkPersonExperienceForPosition(person);
        }
        AddPersonResponse response = new AddPersonResponse();
        if (person.getValid()) {
            serviceStatus.setStatus(Status.SUCCESS.name());
        } else {
            serviceStatus.setStatus(Status.ERROR.name());
        }
        response.setServiceStatus(serviceStatus);
        response.setPerson(person);
        return response;

    }

    public UpdatePersonResponse updatePersonValidator(Person person) {
        personChecker.checkPersonRequiredFields(person);
        ServiceStatus serviceStatus = new ServiceStatus();
        if (person.getValid()) {
            serviceStatus = personChecker.checkPersonSalary(person);
        }
        UpdatePersonResponse response = new UpdatePersonResponse();
        if (person.getValid()) {
            serviceStatus.setStatus(Status.SUCCESS.name());
        } else {
            serviceStatus.setStatus(Status.ERROR.name());
        }
        response.setServiceStatus(serviceStatus);
        response.setPerson(person);
        return response;
    }

}
