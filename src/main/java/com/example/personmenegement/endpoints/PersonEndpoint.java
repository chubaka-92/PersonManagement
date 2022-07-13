package com.example.personmenegement.endpoints;

import com.example.personmenegement.services.PersonServiceImp;
import com.example.personmenegement.soap.person.*;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class PersonEndpoint {
    private static final String NAMESPACE_URI = "http://example.com/personmenegement/persons";

    private final PersonServiceImp personService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonByIdRequest")
    @ResponsePayload
    public GetPersonByIdResponse getPersonById(@RequestPayload GetPersonByIdRequest request) {
        return personService.getPersonById(request.getId());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addPersonRequest")
    @ResponsePayload
    public AddPersonResponse addPerson(@RequestPayload Person request) {
        return personService.addNewPerson(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updatePersonRequest")
    @ResponsePayload
    public UpdatePersonResponse updatePerson(@RequestPayload UpdatePersonRequest request) {
        return personService.updatePerson(request.getPerson());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deletePersonRequest")
    @ResponsePayload
    public DeletePersonResponse deletePersonById(@RequestPayload DeletePersonRequest request) {
        return personService.deletePerson(request.getId());
    }
}
