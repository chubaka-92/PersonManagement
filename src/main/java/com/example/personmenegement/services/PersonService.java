package com.example.personmenegement.services;

import com.example.personmenegement.entity.PersonEntity;
import com.example.personmenegement.repository.PersonRepository;
import com.example.personmenegement.soap.person.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class PersonService { // todo делай время от времени реформат кода Ctrl + Alt + L
    private final PersonRepository personRepository;

    // todo почему статика и почему публичная?
    public static final Function<PersonEntity, Person> functionEntityToSoap = personEntity -> {
        Person person = new Person(); // todo используй builder lombok
        person.setId(personEntity.getId());
        person.setName(personEntity.getName());
        person.setAge(personEntity.getAge());
        person.setEmail(personEntity.getEmail());
        person.setSalary(personEntity.getSalary());
        person.setPosition(personEntity.getPosition());
        return person;
    };

    // todo преобразование вынести в отдельный сервис. Класс-репозиторий по-хорошему должен возвращать объект сущности,
    //  которой орудует PersonRepository
    public Person getById(long id) {
         PersonEntity personEntity =  personRepository.findPersonById(id);

        return functionEntityToSoap.apply(personEntity);
    }

    // todo @Transactional
    public void addPerson(PersonEntity personEntity) {
        personRepository.save(personEntity);
    }

    // todo @Transactional
    public void updatePerson(PersonEntity personEntity) {
        personRepository.save(personEntity);
    }
    // todo @Transactional
    public void deletePerson(long personId) {
        personRepository.deleteById(personId);
    }
}
