package com.example.personmenegement.services.mapper;

import com.example.personmenegement.entity.PersonEntity;
import com.example.personmenegement.soap.person.Person;
import com.example.personmenegement.types.Position;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class PersonMapper { // todo сделать для него интерфейс и использовать интерфейс
    public Person personEntityToPerson(PersonEntity personEntity) {
        return Person.builder()
                .id(String.valueOf(personEntity.getId()))
                .name(personEntity.getName())
                .age(String.valueOf(personEntity.getAge()))
                .email(personEntity.getEmail())
                .salary(String.valueOf(personEntity.getSalary()))
                .position(personEntity.getPosition().toString())
                .valid(true)
                .experience(personEntity.getExperience().toString())
                .build();
    }

    public PersonEntity personToPersonEntity(Person person) {
        return PersonEntity.builder()
                .id(getId(person))
                .name(person.getName())
                .age(Integer.valueOf(person.getAge()))
                .email(person.getEmail())
                .salary(new BigDecimal(person.getSalary()))
                .position(Position.getDefine(person.getPosition()))
                .experience(Double.valueOf(person.getExperience()))
                .build();
    }

    private Long getId(Person person) {
        if (!(person.getId() == null)) {
            return Long.valueOf(person.getId());
        } else {
            return null;
        }
        // todo если можно сдлеать без инверсии, то делай без инверсии + у тебя лишний else
//        if (person.getId() == null){
//            return null;
//        }
//        return Long.valueOf(person.getId());
        // или
//        if (Objects.nonNull(person.getId())){
//            return Long.valueOf(person.getId());
//        }
//        return null;
    }
}
