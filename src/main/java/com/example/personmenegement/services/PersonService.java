package com.example.personmenegement.services;

import com.example.personmenegement.entity.PersonEntity;
import com.example.personmenegement.repository.PersonRepository;
import com.example.personmenegement.soap.person.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public static final Function<PersonEntity, Person> functionEntityToSoap = personEntity -> {
        Person person = new Person();
        person.setId(personEntity.getId());
        person.setName(personEntity.getName());
        person.setAge(personEntity.getAge());
        person.setEmail(personEntity.getEmail());
        person.setSalary(personEntity.getSalary());
        person.setPosition(personEntity.getPosition());
        return person;
    };

    public Person getById(long id) {
         PersonEntity personEntity =  personRepository.findPersonById(id);

        return functionEntityToSoap.apply(personEntity);
    }

    public void addPerson(PersonEntity personEntity) {
        personRepository.save(personEntity);
    }

    public void updatePerson(PersonEntity personEntity) {
        personRepository.save(personEntity);
    }
    public void deletePerson(long personId) {
        personRepository.deleteById(personId);
    }
}
