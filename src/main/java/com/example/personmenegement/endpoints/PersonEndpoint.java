package com.example.personmenegement.endpoints;

import com.example.personmenegement.entity.PersonEntity;
import com.example.personmenegement.services.PersonService;
import com.example.personmenegement.soap.person.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class PersonEndpoint {
    private static final String NAMESPACE_URI = "http://example.com/personmenegement/persons";


    private final PersonService personService;
    //private final ServiceStatus serviceStatus;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonByIdRequest")
    @ResponsePayload
    public GetPersonByIdResponse getPersonById(@RequestPayload GetPersonByIdRequest request) {
        GetPersonByIdResponse response = new GetPersonByIdResponse();
        Person person = personService.getById(request.getId());
        response.setPerson(person);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addPersonRequest")
    @ResponsePayload
    public AddPersonResponse addPerson(@RequestPayload AddPersonRequest request) {
        AddPersonResponse response = new AddPersonResponse();
        PersonEntity personEntity = new PersonEntity();
        ServiceStatus serviceStatus = new ServiceStatus();

        BeanUtils.copyProperties(request,personEntity);

        personService.addPerson(personEntity);

        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Person Added Successfully");
        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updatePersonRequest")
    @ResponsePayload
    public UpdatePersonResponse updatePerson(@RequestPayload UpdatePersonRequest request) {
        UpdatePersonResponse response = new UpdatePersonResponse();
        PersonEntity personEntity = new PersonEntity();
        ServiceStatus serviceStatus = new ServiceStatus();

        BeanUtils.copyProperties(request.getPerson(),personEntity);

        personService.updatePerson(personEntity);

        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Person Updated Successfully");
        response.setServiceStatus(serviceStatus);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deletePersonRequest")
    @ResponsePayload
    public DeletePersonResponse deletePersonById(@RequestPayload DeletePersonRequest request) {
        DeletePersonResponse response = new DeletePersonResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        personService.deletePerson(request.getId());

        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Person Deleted Successfully");
        response.setServiceStatus(serviceStatus);

        return response;
    }
}
