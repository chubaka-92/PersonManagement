package com.example.personmenegement.services;

import com.example.personmenegement.api.*;
import com.example.personmenegement.entity.PersonEntity;
import com.example.personmenegement.soap.person.*;
import com.example.personmenegement.types.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
public class PersonServiceImp implements PersonService {
    private static final String PERSON_NOT_FOUND = "personNotFound";
    private final PersonDAO personDao;
    private final PersonMapper personMapper;
    private final PersonValidation personValidation;

    private final ResourceBundleService errorMsg; //todo назвать переменную, как название класса. + нежелательно сокращать слова в переменных

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
        AddPersonResponse response = new AddPersonResponse();
        response.setPerson(personValidation.validate(person));

        ServiceStatus serviceStatus = new ServiceStatus();
        if (response.getPerson() == null) {
            serviceStatus.setStatus(Status.SUCCESS.name());
            response.setPerson(personMapper.personEntityToPerson(
                    personDao.addPerson(personMapper.personToPersonEntity(person)))); //todo много вложенных вызовов внутри метода. Так ухудшается читаемость кода
        } else {
            serviceStatus.setStatus(Status.ERROR.name());
        }
        response.setServiceStatus(serviceStatus);
        return response;
    }

    public UpdatePersonResponse updatePerson(Person person) {
        UpdatePersonResponse response = new UpdatePersonResponse();
        response.setPerson(personValidation.validate(person));

        ServiceStatus serviceStatus = new ServiceStatus();
        if (response.getPerson() == null) {
            serviceStatus.setStatus(Status.SUCCESS.name());
            response.setPerson(personMapper.personEntityToPerson(
                    personDao.updatePerson(personMapper.personToPersonEntity(person)))); //todo много вложенных вызовов внутри метода
        } else {
            serviceStatus.setStatus(Status.ERROR.name());
        }
        response.setServiceStatus(serviceStatus);
        return response;
    }

    public DeletePersonResponse deletePerson(long id) {
        DeletePersonResponse response = new DeletePersonResponse();
        response.setServiceStatus(processDeletePerson(id));
        return response;
    }

    private ServiceStatus processDeletePerson(long id) {
        ServiceStatus serviceStatus = new ServiceStatus();
        if (personDao.findPersonById(id) == null) {
            serviceStatus.setStatus(Status.ERROR.name());
            serviceStatus.setMessage(MessageFormat.format(errorMsg.getString(PERSON_NOT_FOUND), id));
        } else {
            serviceStatus.setStatus(Status.SUCCESS.name());
            personDao.deletePersonById(id);
        }
        return serviceStatus;
    }
}
