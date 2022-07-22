package com.example.personmenegement.services;

import com.example.personmenegement.api.*;
import com.example.personmenegement.entity.PersonEntity;
import com.example.personmenegement.soap.person.*;
import com.example.personmenegement.types.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ResourceBundle;

@Service
@RequiredArgsConstructor
public class PersonServiceImp implements PersonService {
    private static final String PERSON_NOT_FOUND = "personNotFound";
    private final PersonDAO personDao;
    private final PersonMapper personMapper;
    private final PersonValidation personValidation;
     //todo ResourceBundle используется PersonCheckerImp, сделать отдельный сервис работающий с ResourceBundle
    //  Done
    private final ResourceBundleService errorMsg;

    public GetPersonByIdResponse getPersonById(Long id) {
        GetPersonByIdResponse response = new GetPersonByIdResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        PersonEntity personEntity = personDao.findPersonById(id);

        if (personEntity == null) {
            serviceStatus.setStatus(Status.ERROR.name());
            serviceStatus.setMessage(MessageFormat.format(errorMsg.getString(PERSON_NOT_FOUND), id));
        } else {
            response.setPerson(personMapper.personEntityToPerson(personEntity));
            serviceStatus.setStatus(Status.SUCCESS.name());
        }
        response.setServiceStatus(serviceStatus);
        return response;
    }

    public AddPersonResponse addNewPerson(Person person) {
        AddPersonResponse response = personValidation.addPersonValidator(person);

        if (response.getServiceStatus().getStatus().equals(Status.SUCCESS.name())) {
            response.getPerson().setId(String.valueOf(personDao.addPerson(personMapper.personToPersonEntity(person)))); //todo плохой перенос  // DONE
        }
        return response;
    }

    public UpdatePersonResponse updatePerson(Person person) {
        UpdatePersonResponse response = personValidation.updatePersonValidator(person);

        if (response.getServiceStatus().getStatus().equals(Status.SUCCESS.name())) {
            response.getPerson()
                    .setId(String.valueOf(personDao.updatePerson(personMapper.personToPersonEntity(person)))); //todo плохой перенос  // DONE
        }
        return response;
    }

    public DeletePersonResponse deletePerson(long id) {
        DeletePersonResponse response = new DeletePersonResponse();
        response.setServiceStatus(processDeletePerson(id));
        return response;
    }

    private ServiceStatus processDeletePerson(long id) {
        ServiceStatus serviceStatus = new ServiceStatus();
        if (personDao.findPersonById(id) == null) { //todo в методе delete какая доп логика, либо перенести, либо разбить на приватные методы // DONE
            serviceStatus.setStatus(Status.ERROR.name());
            serviceStatus.setMessage(MessageFormat.format(errorMsg.getString(PERSON_NOT_FOUND), id));
        } else {
            serviceStatus.setStatus(Status.SUCCESS.name());
            personDao.deletePersonById(id);
        }
        return serviceStatus;
    }
}
